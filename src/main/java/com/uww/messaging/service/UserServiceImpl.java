package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.MessagingApplication;
import com.uww.messaging.contract.UserRoleService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.UserDisplay;
import com.uww.messaging.display.UserInvitationForm;
import com.uww.messaging.display.UserInvitationResponse;
import com.uww.messaging.model.user.User;
import com.uww.messaging.model.user.UserInvitation;
import com.uww.messaging.model.user.UserRole;
import com.uww.messaging.repository.user.UserInvitationRepository;
import com.uww.messaging.repository.user.UserRepository;
import com.uww.messaging.security.AuthenticationUtil;

import com.uww.messaging.util.UtilFileUpload;
import com.uww.messaging.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by horvste on 1/18/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserInvitationRepository userInvitationRepository;

    private UserRoleService userRoleService;

    private UserRepository userRepository;

    private MessagingApplication messagingApplication;

    @Autowired
    public UserServiceImpl(UserInvitationRepository userInvitationRepository, UserRoleService userRoleService, UserRepository userRepository, MessagingApplication messagingApplication) {
        this.userInvitationRepository = userInvitationRepository;
        this.userRoleService = userRoleService;
        this.userRepository = userRepository;
        this.messagingApplication = messagingApplication;
    }

    private static final String PENDING = "pending";
    private static final String ACCEPTED = "accepted";

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void save(final User user) {
        userRepository.save(user);
    }

    public void save(User user, UserRole userRole) {
        Preconditions.checkNotNull(user, "user");
        Preconditions.checkNotNull(userRole, "userRole");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        userRole.setUserId(user.getUserId());
        userRoleService.save(userRole);
    }

    public void deleteAll() {
        userRoleService.deleteAll();
        userRepository.deleteAll();
    }

    public void deleteAll(String role) {
        String roleSuffix = role;
        String rolePrefix = "ROLE_";
        if (!role.startsWith(rolePrefix)) {
            role = rolePrefix + role;
        }

        List<UserRole> userRoleList = userRoleService.findByAuthority(role);

        if (userRoleList.size() == 0) {
            userRoleList = userRoleService.findByAuthority(roleSuffix);
        }

        for (int i = 0; i < userRoleList.size(); i++) {
            UserRole userRole = userRoleList.get(i);
            User needsDeletingUser = userRepository.findOne(userRole.getUserId());
            userRepository.delete(needsDeletingUser);
            userRoleService.delete(userRole);
        }
    }

    @Override
    public User getLoggedInUser(Authentication auth) {
        UserRole userRole = AuthenticationUtil.authenticationToRole(auth);
        return findUserById(userRole.getUserId());
    }

    @Override
    public List<String> findUsersStartingWith(String username) {
        List<User> users = userRepository.findByUsernameStartingWith(username);
        List<String> usernames = new ArrayList<>();
        users.forEach(u -> {
            usernames.add(u.getUsername());
        });
        return usernames;
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }

    @Override
    public boolean exists(int userId) {
        return userRepository.findOne(userId) != null;
    }

    @Override
    public boolean exists(String username) {
        return userRepository.findByUsername(username).size() != 0;
    }

    @Override
    public void setAvatar(int userId, MultipartFile avatarUpload) {
        User loggedInUser = findUserById(userId);
        Preconditions.checkNotNull(loggedInUser);
        Preconditions.checkNotNull(avatarUpload);

        try {
            File file = UtilFileUpload.transferFileToDirWithRandomName(messagingApplication.userDownloadDir, avatarUpload);
            loggedInUser.setAvatarPicture(file.getAbsolutePath());
            userRepository.save(loggedInUser);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

    }

    @Override
    public int getLoggedInUserId(Authentication authentication) {
        return getLoggedInUser(authentication).getUserId();
    }


    @Override
    public Set<UserDisplay> findUsersLackingInvitationsStartingWith(int loggedInUserId, String username) {
        List<User> users = userRepository.findByUsernameStartingWith(username);
        Set<UserDisplay> userDisplays = new HashSet<>();
        users.forEach(u -> {
            int userStartingWithUserId = u.getUserId();

            List<UserInvitation> userInvitationWithLoggedInFrom = userInvitationRepository.findByFromUserIdAndToUserId(loggedInUserId, userStartingWithUserId);
            List<UserInvitation> userInvitationWithUserStartingFrom = userInvitationRepository.findByFromUserIdAndToUserId(userStartingWithUserId, loggedInUserId);

            if (userInvitationWithLoggedInFrom.size() == 0 && userInvitationWithUserStartingFrom.size() == 0 &&
                    u.getUserId() != loggedInUserId) {
                userDisplays.add(new UserDisplay(u.getUserId(), u.getUsername()));
            }
        });
        return userDisplays;
    }


    @Override
    public void sendInvitation(int loggedInUserId, UserInvitationForm userInvitationForm) {
        Preconditions.checkNotNull(userInvitationForm);

        UserInvitation userInvitation = new UserInvitation();
        userInvitation.setFromUserId(loggedInUserId);

        User userToInvite = userRepository.findByUsername(userInvitationForm.getName()).get(0);

        User loggedInUser = findUserById(loggedInUserId);
        boolean tryingToSendInvitationToSelf = loggedInUser.getUsername().equals(userToInvite.getUsername());

        if (tryingToSendInvitationToSelf) {
            throw new IllegalArgumentException("Attempting to send invitation to self: " + loggedInUser.getUsername());
        }

        boolean alreadyHasAnInvitation = userInvitationRepository.findByFromUserIdAndToUserId(
                loggedInUserId,
                userToInvite.getUserId()
        ).size() > 0;


        if (alreadyHasAnInvitation) {
            throw new IllegalArgumentException("Duplicate Invitation To: " + userToInvite.getUsername());
        }
        alreadyHasAnInvitation = userInvitationRepository.findByFromUserIdAndToUserId(
                userToInvite.getUserId(),
                loggedInUserId
        ).size() > 0;

        if (alreadyHasAnInvitation) {
            throw new IllegalArgumentException("Duplicate Invitation To: " + userToInvite.getUsername());
        }

        userInvitation.setToUserId(userToInvite.getUserId());
        userInvitation.setStatus(PENDING);
        userInvitation.setMessage(userInvitationForm.getMessage());
        userInvitationRepository.save(userInvitation);
    }

    @Override
    public void acceptInvitation(int loggedInUserId, int fromUserId, int userInvitationId) {
        UserInvitation invite = userInvitationRepository.findOne(userInvitationId);
        if (invite.getFromUserId() == fromUserId && invite.getToUserId() == loggedInUserId
                && invite.getStatus().equals(PENDING)) {
            invite.setStatus(ACCEPTED);
            userInvitationRepository.save(invite);
            return;
        }
        throw new IllegalArgumentException("Cannot accept invitation");
    }

    @Override
    public List<UserInvitationResponse> findAllPendingInvitations(int userId) {
        List<UserInvitation> invitations = userInvitationRepository.findByToUserIdAndStatusEquals(userId, PENDING);
        List<UserInvitationResponse> userInvitationResponses = new ArrayList<>();
        invitations.forEach(invite -> {
            User thatSent = findUserById(invite.getFromUserId());
            UserInvitationResponse userInvitationResponse = new UserInvitationResponse(thatSent.getUserId(),
                    thatSent.getUsername(),
                    invite.getMessage(),
                    invite.getUserInvitationId());
            userInvitationResponses.add(userInvitationResponse);
        });
        return userInvitationResponses;
    }

    @Override
    public List<User> findAcceptedInvitationUsers(int userId) {
        List<UserInvitation> userInvitations = userInvitationRepository.findInvitationsByUserId(
                userId,
                ACCEPTED
        );
        List<User> users = new ArrayList<>();
        userInvitations.forEach(userInvitation -> {
            if (userInvitation.getFromUserId() == userId) {
                users.add(findUserById(userInvitation.getToUserId()));
                return;
            }
            users.add(findUserById(userInvitation.getFromUserId()));
        });
        return users;
    }


}

package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.contract.UserRoleService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.UserInvitationForm;
import com.uww.messaging.display.UserInvitationResponse;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserInvitation;
import com.uww.messaging.model.UserRole;
import com.uww.messaging.repository.UserInvitationRepository;
import com.uww.messaging.repository.UserRepository;
import com.uww.messaging.security.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInvitationRepository userInvitationRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRepository userRepository;

    private static final String PENDING = "pending";
    private static final String ACCEPTED = "accepted";

    public User findUserById(int userId) {
        return userRepository.findOne(userId);
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
        if (!role.startsWith(rolePrefix))
            role = rolePrefix + role;

        List<UserRole> userRoleList = userRoleService.findByAuthority(role);

        if (userRoleList.size() == 0)
            userRoleList = userRoleService.findByAuthority(roleSuffix);

        for (int i = 0; i < userRoleList.size(); i++) {
            UserRole userRole = userRoleList.get(i);
            User needsDeletingUser = userRepository.findOne(userRole.getUserId());
            userRepository.delete(needsDeletingUser);
            userRoleService.delete(userRole);
        }
    }

    @Override
    public User userByAuthentication(Authentication auth) {
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
    public void sendInvitation(int loggedInUserId, UserInvitationForm userInvitationForm) {
        Preconditions.checkNotNull(userInvitationForm);

        UserInvitation userInvitation = new UserInvitation();
        userInvitation.setFromUserId(loggedInUserId);

        User userToInvite = userRepository.findByUsername(userInvitationForm.getName())
                .get(0);

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
            UserInvitationResponse userInvitationResponse = new UserInvitationResponse(thatSent.getUserId(), thatSent.getUsername(), invite.getMessage(), invite.getUserInvitationId());
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

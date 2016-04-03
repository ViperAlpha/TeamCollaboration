package com.uww.messaging.contract;

import com.uww.messaging.display.UserInvitationForm;
import com.uww.messaging.display.UserInvitationResponse;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserRole;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */
public interface UserService {
    User findUserById(int userId);

	void save(User user);

    void save(User user, UserRole userRole);

    void deleteAll();

    void deleteAll(String role);

    User userByAuthentication(Authentication auth);

    List<String> findUsersLackingInvitationsStartingWith(int loggedInUserId, String username);

    void sendInvitation(int loggedInUserId, UserInvitationForm userInvitationForm);

    void acceptInvitation(int loggedInUserId, int fromUserId, int userInvitationId);

    List<UserInvitationResponse> findAllPendingInvitations(int userId);

    List<User> findAcceptedInvitationUsers(int userId);

    List<String> findUsersStartingWith(String username);
}

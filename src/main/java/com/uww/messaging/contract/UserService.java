package com.uww.messaging.contract;

import com.uww.messaging.model.User;
import com.uww.messaging.model.UserRole;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */
public interface UserService {
    User findUserById(int userId);

    void save(User user, UserRole userRole);

    void deleteAll();

    void deleteAll(String role);

    User userByAuthentication(Authentication auth);

    List<User> findUsersStartingWith(String username);
}

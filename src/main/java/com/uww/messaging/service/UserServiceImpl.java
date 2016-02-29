package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.contract.UserRoleService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.User;
import com.uww.messaging.model.UserRole;
import com.uww.messaging.repository.UserRepository;
import com.uww.messaging.security.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRepository userRepository;

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
}

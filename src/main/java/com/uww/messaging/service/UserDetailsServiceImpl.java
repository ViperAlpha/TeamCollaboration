package com.uww.messaging.service;

import com.uww.messaging.model.User;
import com.uww.messaging.model.UserRole;
import com.uww.messaging.repository.UserRepository;
import com.uww.messaging.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 1/10/16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;



    public UserDetailsServiceImpl() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userRepository.findByUsername(username);
        if (userList == null || userList.size() == 0)
            throw new UsernameNotFoundException(username);
        User user = userList.get(0);

        List<UserRole> userRoleList = userRoleRepository.findByUserId(user.getUserId());
        if (userRoleList == null || userRoleList.size() == 0)
            throw new UsernameNotFoundException(username);
        UserRole userRole = userRoleList.get(0);


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(userRole);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}

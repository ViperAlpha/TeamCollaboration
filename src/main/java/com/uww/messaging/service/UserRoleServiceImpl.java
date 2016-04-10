package com.uww.messaging.service;

import com.uww.messaging.contract.UserRoleService;
import com.uww.messaging.model.user.UserRole;
import com.uww.messaging.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void save(UserRole userRole) {
        if (userRole == null)
            throw new IllegalArgumentException("userRole is null");
        userRoleRepository.save(userRole);
    }

    public void deleteAll() {
        userRoleRepository.deleteAll();
    }

    public List<UserRole> findByAuthority(String authority) {
        return userRoleRepository.findByAuthority(authority);
    }

    public void delete(UserRole userRole){
        userRoleRepository.delete(userRole);
    }
}

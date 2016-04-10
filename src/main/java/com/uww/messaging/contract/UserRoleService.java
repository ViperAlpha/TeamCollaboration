package com.uww.messaging.contract;

import com.uww.messaging.model.user.UserRole;

import java.util.List;

/**
 * Created by horvste on 1/18/16.
 */

public interface UserRoleService {
    void save(UserRole userRole);

    void deleteAll();

    List<UserRole> findByAuthority(String authority);

    void delete(UserRole userRole);
}

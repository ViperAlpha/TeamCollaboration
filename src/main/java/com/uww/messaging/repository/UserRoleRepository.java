package com.uww.messaging.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uww.messaging.model.UserRole;

import java.util.List;

/**
 * Created by horvste on 1/10/16.
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    List<UserRole> findByUserId(int userId);
    List<UserRole> findByAuthority(String authority);
}

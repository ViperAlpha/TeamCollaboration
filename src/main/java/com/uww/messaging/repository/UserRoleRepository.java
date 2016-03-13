package com.uww.messaging.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uww.messaging.model.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    List<UserRole> findByUserId(int userId);
    List<UserRole> findByAuthority(String authority);
}

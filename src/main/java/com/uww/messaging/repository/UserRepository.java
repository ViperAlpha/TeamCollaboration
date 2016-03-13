package com.uww.messaging.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uww.messaging.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findByUsername(String username);
    List<User> findByUsernameStartingWith(String username);
}

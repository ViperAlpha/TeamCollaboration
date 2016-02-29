package com.uww.messaging.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uww.messaging.model.User;

import java.util.List;

/**
 * Created by horvste on 1/10/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findByUsername(String username);
}

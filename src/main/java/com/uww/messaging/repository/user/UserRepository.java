package com.uww.messaging.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uww.messaging.model.user.User;

import java.util.List;

/**
 * Created by horvste on 1/10/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findByUsername(String username);
    List<User> findByUsernameStartingWith(String username);
}

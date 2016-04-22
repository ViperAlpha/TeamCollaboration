package com.uww.messaging.mapper;

import com.uww.messaging.model.user.User;
import com.uww.messaging.display.UserRegistration;

/**
 * Created by horvste on 1/18/16.
 */
public class UserRegistrationMapper {

    public static User mapToUser(UserRegistration userRegistration) {
        return new User(
                userRegistration.getEmail(),
                userRegistration.getPassword(),
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getPhoneNumber(),
                null
        );
    }
}

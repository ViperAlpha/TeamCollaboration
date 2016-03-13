package com.uww.messaging.mapper;

import com.uww.messaging.model.User;
import com.uww.messaging.display.UserRegistration;

public class UserRegistrationMapper {

    public static User mapToUser(UserRegistration userRegistration) {
        return new User(
                userRegistration.getEmail(),
                userRegistration.getPassword(),
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getPhoneNumber()
        );
    }
}

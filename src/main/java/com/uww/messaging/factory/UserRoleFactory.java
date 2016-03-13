package com.uww.messaging.factory;

import com.uww.messaging.model.UserRole;

public class UserRoleFactory {

    public static final String USER = "ROLE_USER";

    public static UserRole getWebsiteUserRole() {
        return new UserRole(
                USER
        );
    }
}

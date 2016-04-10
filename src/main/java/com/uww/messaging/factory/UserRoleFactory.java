package com.uww.messaging.factory;

import com.uww.messaging.model.user.UserRole;

/**
 * Created by horvste on 1/18/16.
 */
public class UserRoleFactory {

    public static final String USER = "ROLE_USER";

    public static UserRole getWebsiteUserRole() {
        return new UserRole(
                USER
        );
    }
}

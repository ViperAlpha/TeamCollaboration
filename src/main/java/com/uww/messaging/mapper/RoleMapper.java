package com.uww.messaging.mapper;

/**
 * Created by horvste on 1/19/16.
 */
public class RoleMapper {
    public static String mapRoleToRoute(String role) {
        switch (role) {
            case "ROLE_ADMIN":
                return "admin";
            case "ROLE_USER":
                return "user";
            case "USER":
                return "user";
            default:
                return null;
        }
    }
}

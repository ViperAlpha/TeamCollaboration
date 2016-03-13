package com.uww.messaging.mapper;

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

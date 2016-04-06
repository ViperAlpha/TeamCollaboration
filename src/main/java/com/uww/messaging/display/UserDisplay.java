package com.uww.messaging.display;

/**
 * Created by horvste on 4/6/16.
 */
public class UserDisplay {
    private int userId;
    private String username;

    public UserDisplay(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

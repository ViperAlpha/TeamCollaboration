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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDisplay that = (UserDisplay) o;

        if (userId != that.userId) return false;
        return username != null ? username.equals(that.username) : that.username == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}

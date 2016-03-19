package com.uww.messaging.display;

/**
 * Created by horvste on 3/19/16.
 */
public class UserInvitationResponse {
    private int fromUserId;
    private String username;
    private String message;
    private int userInvitationId;

    public UserInvitationResponse(int fromUserId, String username, String message, int userInvitationId) {
        this.fromUserId = fromUserId;
        this.username = username;
        this.message = message;
        this.userInvitationId = userInvitationId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public int getUserInvitationId() {
        return userInvitationId;
    }
}

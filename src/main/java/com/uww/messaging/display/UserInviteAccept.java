package com.uww.messaging.display;

/**
 * Created by horvste on 3/19/16.
 */
public class UserInviteAccept {
    private int fromUserId;
    private int userInvitationId;

    public UserInviteAccept() {
    }

    public UserInviteAccept(int fromUserId, int userInvitationId) {
        this.fromUserId = fromUserId;
        this.userInvitationId = userInvitationId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getUserInvitationId() {
        return userInvitationId;
    }

    public void setUserInvitationId(int userInvitationId) {
        this.userInvitationId = userInvitationId;
    }
}

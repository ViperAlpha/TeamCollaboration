package com.uww.messaging.model;

import javax.persistence.*;

/**
 * Created by horvste on 3/18/16.
 */
@Entity
public class UserInvitation {
    private int userInvitationId;
    private int fromUserId;
    private int toUserId;
    private String message;
    private String status;

    @Id
    @GeneratedValue
    @Column(name = "userInvitationId", nullable = false)
    public int getUserInvitationId() {
        return userInvitationId;
    }

    public void setUserInvitationId(int userInvitationId) {
        this.userInvitationId = userInvitationId;
    }

    @Basic
    @Column(name = "fromUserId", nullable = false)
    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Basic
    @Column(name = "toUserId", nullable = false)
    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 100)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInvitation that = (UserInvitation) o;

        if (userInvitationId != that.userInvitationId) return false;
        if (fromUserId != that.fromUserId) return false;
        if (toUserId != that.toUserId) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userInvitationId;
        result = 31 * result + fromUserId;
        result = 31 * result + toUserId;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

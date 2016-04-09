package com.uww.messaging.model.user;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by horvste on 3/5/16.
 */
@Entity
public class UserMessage {
    private int userMessageId;
    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private Integer userMessageChatId;
    private Timestamp messageTime;

    public UserMessage() {
    }

    public UserMessage(Integer fromUserId, Integer toUserId, String message, Integer userMessageChatId, Timestamp messageTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message = message;
        this.userMessageChatId = userMessageChatId;
        this.messageTime = messageTime;
    }

    @Id
    @GeneratedValue
    @Column(name = "userMessageId", nullable = false)
    public int getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(int userMessageId) {
        this.userMessageId = userMessageId;
    }

    @Basic
    @Column(name = "fromUserId", nullable = true)
    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Basic
    @Column(name = "toUserId", nullable = true)
    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 500)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "userMessageChatId", nullable = true)
    public Integer getUserMessageChatId() {
        return userMessageChatId;
    }

    public void setUserMessageChatId(Integer userMessageChatId) {
        this.userMessageChatId = userMessageChatId;
    }

    @Basic
    @Column(name = "messageTime", nullable = true)
    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessage that = (UserMessage) o;

        if (userMessageId != that.userMessageId) return false;
        if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) return false;
        if (toUserId != null ? !toUserId.equals(that.toUserId) : that.toUserId != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (userMessageChatId != null ? !userMessageChatId.equals(that.userMessageChatId) : that.userMessageChatId != null)
            return false;
        if (messageTime != null ? !messageTime.equals(that.messageTime) : that.messageTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userMessageId;
        result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
        result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (userMessageChatId != null ? userMessageChatId.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        return result;
    }
}

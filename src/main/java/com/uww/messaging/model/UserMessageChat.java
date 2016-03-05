package com.uww.messaging.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by horvste on 3/5/16.
 */
@Entity
public class UserMessageChat {
    private int userMessageChatId;
    private Integer fromUserId;
    private Integer toUserId;
    private Timestamp chatTime;

    public UserMessageChat() {
    }

    public UserMessageChat(Integer fromUserId, Integer toUserId, Timestamp chatTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.chatTime = chatTime;
    }

    @Id
    @Column(name = "userMessageChatId", nullable = false)
    public int getUserMessageChatId() {
        return userMessageChatId;
    }

    public void setUserMessageChatId(int userMessageChatId) {
        this.userMessageChatId = userMessageChatId;
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
    @Column(name = "chatTime", nullable = true)
    public Timestamp getChatTime() {
        return chatTime;
    }

    public void setChatTime(Timestamp chatTime) {
        this.chatTime = chatTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessageChat that = (UserMessageChat) o;

        if (userMessageChatId != that.userMessageChatId) return false;
        if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) return false;
        if (toUserId != null ? !toUserId.equals(that.toUserId) : that.toUserId != null) return false;
        if (chatTime != null ? !chatTime.equals(that.chatTime) : that.chatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userMessageChatId;
        result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
        result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
        result = 31 * result + (chatTime != null ? chatTime.hashCode() : 0);
        return result;
    }
}

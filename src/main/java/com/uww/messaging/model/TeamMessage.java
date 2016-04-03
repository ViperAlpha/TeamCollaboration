package com.uww.messaging.model;

import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Created by horvste on 2/19/16.
 */
@Entity
public class TeamMessage {
	private int teamMessageId;
	private Integer fromUserId;
	private Integer toTeamId;
	private String message;
	private Timestamp messageTime;

	public TeamMessage() {}

	public TeamMessage(final Integer fromUserId, final Integer toTeamId, final String message, final Timestamp messageTime) {
		this.fromUserId = fromUserId;
		this.toTeamId = toTeamId;
		this.message = message;
		this.messageTime = messageTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "teamMessageId", nullable = false)
	public int getTeamMessageId() {
		return teamMessageId;
	}

	public void setTeamMessageId(int teamMessageId) {
		this.teamMessageId = teamMessageId;
	}

	@Basic
	@Column(name = "fromUserId", nullable = false)
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	@Basic
	@Column(name = "toTeamId", nullable = false)
	public Integer getToTeamId() {
		return toTeamId;
	}

	public void setToTeamId(Integer toTeamId) {
		this.toTeamId = toTeamId;
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
	@Column(name = "messageTime", nullable = true)
	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(final Timestamp messageTime) {
		this.messageTime = messageTime;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMessage that = (TeamMessage) o;

        if (teamMessageId != that.teamMessageId) return false;
        if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) return false;
        if (toTeamId != null ? !toTeamId.equals(that.toTeamId) : that.toTeamId != null) return false;
	    return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = teamMessageId;
        result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
        result = 31 * result + (toTeamId != null ? toTeamId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}

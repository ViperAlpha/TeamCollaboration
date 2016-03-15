package com.uww.messaging.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Reinaldo
 */
@Entity
public class TeamMessageChat {
	private int teamMessageChatId;
	private Integer fromUserId;
	private Integer toTeamId;
	private Timestamp chatTime;

	public TeamMessageChat() {
	}

	public TeamMessageChat(Integer fromUserId, Integer toTeamId, Timestamp chatTime) {
		this.fromUserId = fromUserId;
		this.toTeamId = toTeamId;
		this.chatTime = chatTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "teamMessageChatId", nullable = false)
	public int getTeamMessageChatId() {
		return teamMessageChatId;
	}

	public void setTeamMessageChatId(int teamMessageChatId) {
		this.teamMessageChatId = teamMessageChatId;
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
	@Column(name = "chatTime", nullable = false)
	public Timestamp getChatTime() {
		return chatTime;
	}

	public void setChatTime(Timestamp chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public int hashCode() {
		int result = teamMessageChatId;
		result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
		result = 31 * result + (toTeamId != null ? toTeamId.hashCode() : 0);
		result = 31 * result + (chatTime != null ? chatTime.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		TeamMessageChat that = (TeamMessageChat) o;

		if (teamMessageChatId != that.teamMessageChatId) { return false; }
		if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) { return false; }
		if (toTeamId != null ? !toTeamId.equals(that.toTeamId) : that.toTeamId != null) { return false; }
		if (chatTime != null ? !chatTime.equals(that.chatTime) : that.chatTime != null) { return false; }

		return true;
	}
}

package com.uww.messaging.model;

import java.sql.Timestamp;

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
	private Integer teamId;
	private Timestamp chatTime;

	public TeamMessageChat() {
	}

	public TeamMessageChat(final Integer teamId, final Timestamp chatTime) {
		this.teamId = teamId;
		this.chatTime = chatTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "teamMessageChatId", nullable = false)
	public int getTeamMessageChatId() {
		return teamMessageChatId;
	}

	public void setTeamMessageChatId(final int teamMessageChatId) {
		this.teamMessageChatId = teamMessageChatId;
	}

	@Column(name = "chatTime", nullable = false)
	public Timestamp getChatTime() {
		return chatTime;
	}

	public void setChatTime(final Timestamp chatTime) {
		this.chatTime = chatTime;
	}

	@Column(name = "teamId", nullable = false)
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(final Integer teamId) {
		this.teamId = teamId;
	}
}

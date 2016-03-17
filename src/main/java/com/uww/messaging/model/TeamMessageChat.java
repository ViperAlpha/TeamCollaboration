package com.uww.messaging.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by Reinaldo
 */
@Entity
public class TeamMessageChat {
	private int teamMessageChatId;
	private Integer teamId;
	private Timestamp chatTime;
	private Collection<TeamMember> members;

	public TeamMessageChat() {
	}

	public TeamMessageChat(final List<TeamMember> members, final Integer teamId, final Timestamp chatTime) {
		this.members = members;
		this.teamId = teamId;
		this.chatTime = chatTime;
	}

	@OneToMany( cascade = CascadeType.ALL, targetEntity = TeamMember.class ,mappedBy = "teamMessageChatId")
	public Collection<TeamMember> getMembers() {
		return members;
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

	public void setMembers(final List<TeamMember> members) {
		this.members = members;
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
}

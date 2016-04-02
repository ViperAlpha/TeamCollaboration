package com.uww.messaging.display;

import java.util.Date;

/**
 * Created on 3/22/16
 *
 * @author reinaldo
 */
public class TeamInvitationResponse {
	private int teamInvitationId;
	private String toUserName;
	private String teamName;
	private String message;
	private String status;
	private Date invitationTime;

	public TeamInvitationResponse() {

	}


	public int getTeamInvitationId() {
		return teamInvitationId;
	}

	public void setTeamInvitationId(final int teamInvitationId) {
		this.teamInvitationId = teamInvitationId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(final String toUserName) {
		this.toUserName = toUserName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Date getInvitationTime() {
		return invitationTime;
	}

	public void setInvitationTime(final Date invitationTime) {
		this.invitationTime = invitationTime;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(final String teamName) {
		this.teamName = teamName;
	}
}

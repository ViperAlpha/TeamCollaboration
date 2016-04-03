package com.uww.messaging.display;

/**
 * Created on 4/2/16
 *
 * @author reinaldo
 */
public class TeamInvitationForm {

	private int teamInvitedTo;
	private String invitedUsername;

	public int getTeamInvitedTo() {
		return teamInvitedTo;
	}

	public void setTeamInvitedTo(final int teamInvitedTo) {
		this.teamInvitedTo = teamInvitedTo;
	}

	public String getInvitedUsername() {
		return invitedUsername;
	}

	public void setInvitedUsername(final String invitedUsername) {
		this.invitedUsername = invitedUsername;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	private String message;

}

package com.uww.messaging.display;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
public class TeamMessageRequestBody {
	private int toTeamId;
	private String message;

	public int getToTeamId() {
		return toTeamId;
	}

	public void setToTeamId(final int toTeamId) {
		this.toTeamId = toTeamId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}

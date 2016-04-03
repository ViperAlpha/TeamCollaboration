package com.uww.messaging.display;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created on 4/2/16
 *
 * @author reinaldo
 */
public class TeamMessageDisplay {
	private int teamMessageId;
	private Integer fromUserId;
	private String fromUserName;
	private String fromFirstName;
	private String message;
	private Timestamp messageTime;
	private String prettyTime;

	public TeamMessageDisplay(){}

	public TeamMessageDisplay(final int teamMessageId, final Integer fromUserId, final String fromUserName, final String fromFirstName, final String message, final Timestamp messageTime) {
		this.teamMessageId = teamMessageId;
		this.fromUserId = fromUserId;
		this.fromUserName = fromUserName;
		this.fromFirstName = fromFirstName;
		this.message = message;
		this.messageTime = messageTime;
		this.prettyTime = new SimpleDateFormat("hh:mm a").format(messageTime);
	}

	public int getTeamMessageId() {
		return teamMessageId;
	}

	public void setTeamMessageId(final int teamMessageId) {
		this.teamMessageId = teamMessageId;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(final Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(final String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromFirstName() {
		return fromFirstName;
	}

	public void setFromFirstName(final String fromFirstName) {
		this.fromFirstName = fromFirstName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(final Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public String getPrettyTime() {
		return prettyTime;
	}

	public void setPrettyTime(final String prettyTime) {
		this.prettyTime = prettyTime;
	}
}

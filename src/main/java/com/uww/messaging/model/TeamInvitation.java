package com.uww.messaging.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created on 3/20/16
 *
 * @author reinaldo
 */
@Entity
public class TeamInvitation {
	private Integer teamInvitationId;
	private Integer fromUserId;
	private Integer toUserId;
	private Integer toTeamId;
	private Timestamp invitationTime;
	private String message;
	private String status;

	public static String STATUS_PENDING = "pending", STATUS_ACCEPTED = "accepted", STATUS_REJECTED = "rejected";

	@Id
	@GeneratedValue
	@Column(name = "teamInvitationId", nullable = false)
	public Integer getTeamInvitationId() {
		return teamInvitationId;
	}

	public void setTeamInvitationId(final Integer teamInvitationId) {
		this.teamInvitationId = teamInvitationId;
	}

	@Basic
	@Column(name = "fromUserId", nullable = false)
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(final Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	@Basic
	@Column(name = "toTeamId", nullable = false)
	public Integer getToTeamId() {
		return toTeamId;
	}

	public void setToTeamId(final Integer toTeamId) {
		this.toTeamId = toTeamId;
	}

	@Basic
	@Column(name = "toUserId", nullable = false)
	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(final Integer toUserId) {
		this.toUserId = toUserId;
	}

	@Basic
	@Column(name = "invitationTime", nullable = true)
	public Timestamp getInvitationTime() {
		return invitationTime;
	}

	public void setInvitationTime(final Timestamp invitationTime) {
		this.invitationTime = invitationTime;
	}

	@Basic
	@Column(name = "message", nullable = false, length = 100)
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Basic
	@Column(name = "status", nullable = false, length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		int result = teamInvitationId != null ? teamInvitationId.hashCode() : 0;
		result = 31 * result + (fromUserId != null ? fromUserId.hashCode() : 0);
		result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
		result = 31 * result + (invitationTime != null ? invitationTime.hashCode() : 0);
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		TeamInvitation that = (TeamInvitation) o;

		if (teamInvitationId != null ? !teamInvitationId.equals(that.teamInvitationId) : that.teamInvitationId != null) { return false; }
		if (fromUserId != null ? !fromUserId.equals(that.fromUserId) : that.fromUserId != null) { return false; }
		if (toUserId != null ? !toUserId.equals(that.toUserId) : that.toUserId != null) { return false; }
		if (invitationTime != null ? !invitationTime.equals(that.invitationTime) : that.invitationTime != null) { return false; }
		if (message != null ? !message.equals(that.message) : that.message != null) { return false; }
		if (status != null ? !status.equals(that.status) : that.status != null) { return false; }

		return true;
	}

}

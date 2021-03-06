package com.uww.messaging.model.team;

import javax.persistence.*;

/**
 * Created by horvste on 2/20/16.
 */
@Entity
public class TeamMember {
	private int teamMemberId;
	private Integer userId;
	private Integer teamId;

	public TeamMember() {
	}

	public TeamMember(final Integer userId, final Integer teamId) {
		this.userId = userId;
		this.teamId = teamId;
	}

	@Id
	@GeneratedValue
	@Column(name = "teamMemberId", nullable = false)
	public int getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(int teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	@Column(name = "userId", nullable = true)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "teamId", nullable = true)
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


	@Override
    public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		TeamMember that = (TeamMember) o;

		return teamMemberId == that.teamMemberId && (userId != null ?
		                                             userId.equals(that.userId) :
		                                             that.userId == null && (teamId != null ? teamId.equals(that.teamId) : that.teamId == null));

	}

    @Override
    public int hashCode() {
        int result = teamMemberId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}

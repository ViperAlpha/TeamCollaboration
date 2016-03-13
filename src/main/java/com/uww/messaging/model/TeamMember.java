package com.uww.messaging.model;

import javax.persistence.*;

@Entity
public class TeamMember {
    private int teamMemberId;
    private Integer userId;
    private Integer teamId;

    @Id
    @GeneratedValue
    @Column(name = "teamMemberId", nullable = false)
    public int getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(int teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    @Basic
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        if (teamMemberId != that.teamMemberId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamMemberId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}

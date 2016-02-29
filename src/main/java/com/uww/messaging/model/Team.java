package com.uww.messaging.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by horvste on 2/19/16.
 */
@Entity
public class Team {
    private int teamId;
    private String teamName;
    private String teamDescription;
    private Date createdTime;

    @Id
    @GeneratedValue
    @Column(name = "teamId", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "teamName", nullable = false, length = 20)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "teamDescription", nullable = true, length = 100)
    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdTime", nullable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamId != team.teamId) return false;
        if (teamName != null ? !teamName.equals(team.teamName) : team.teamName != null) return false;
        if (teamDescription != null ? !teamDescription.equals(team.teamDescription) : team.teamDescription != null)
            return false;
        if (createdTime != null ? !createdTime.equals(team.createdTime) : team.createdTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (teamDescription != null ? teamDescription.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }
}

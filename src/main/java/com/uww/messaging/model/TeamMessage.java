package com.uww.messaging.model;

import javax.persistence.*;

/**
 * Created by horvste on 2/19/16.
 */
@Entity
public class TeamMessage {
    private int teamMessageId;
    private Integer fromUserid;
    private Integer toTeamId;
    private String message;

    @Id
    @GeneratedValue
    @Column(name = "teamMessageId", nullable = false)
    public int getTeamMessageId() {
        return teamMessageId;
    }

    public void setTeamMessageId(int teamMessageId) {
        this.teamMessageId = teamMessageId;
    }

    @Basic
    @Column(name = "fromUserid", nullable = true)
    public Integer getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(Integer fromUserid) {
        this.fromUserid = fromUserid;
    }

    @Basic
    @Column(name = "toTeamId", nullable = true)
    public Integer getToTeamId() {
        return toTeamId;
    }

    public void setToTeamId(Integer toTeamId) {
        this.toTeamId = toTeamId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 500)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMessage that = (TeamMessage) o;

        if (teamMessageId != that.teamMessageId) return false;
        if (fromUserid != null ? !fromUserid.equals(that.fromUserid) : that.fromUserid != null) return false;
        if (toTeamId != null ? !toTeamId.equals(that.toTeamId) : that.toTeamId != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamMessageId;
        result = 31 * result + (fromUserid != null ? fromUserid.hashCode() : 0);
        result = 31 * result + (toTeamId != null ? toTeamId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}

package com.uww.messaging.display;

/**
 * Created on 4/2/16
 *
 * @author reinaldo
 */
public class TeamInvitationForm {
    private int teamId;
    private String invitedUserName;
    private String message;

    public TeamInvitationForm() {
    }

    public TeamInvitationForm(int teamId, String invitedUserName, String message) {
        this.teamId = teamId;
        this.invitedUserName = invitedUserName;
        this.message = message;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getInvitedUserName() {
        return invitedUserName;
    }

    public void setInvitedUserName(String invitedUserName) {
        this.invitedUserName = invitedUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
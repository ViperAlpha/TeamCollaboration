package com.uww.messaging.display;

/**
 * Created by horvste on 4/7/16.
 */
public class Invitation {
    private String teamOrIndv;
    private String teamName;
    private String message;
    private String name;

    public Invitation(String teamOrIndv, String teamName, String message, String name) {
        this.teamOrIndv = teamOrIndv;
        this.teamName = teamName;
        this.message = message;
        this.name = name;
    }

    public Invitation() {
    }

    public void setTeamOrIndv(String teamOrIndv) {
        this.teamOrIndv = teamOrIndv;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTeamInvite() {
        return teamOrIndv.equals("Team");
    }

    public String getName() {
        return name;
    }

    public boolean isIndvidualInvite() {
        return teamOrIndv.equals("Individual");
    }

    public String getTeamOrIndv() {
        return teamOrIndv;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getMessage() {
        return message;
    }
}

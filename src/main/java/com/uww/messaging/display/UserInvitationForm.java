package com.uww.messaging.display;

/**
 * Created by horvste on 3/18/16.
 */
public class UserInvitationForm {
    private String name;
    private String message;

    public UserInvitationForm() {
    }

    public UserInvitationForm(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

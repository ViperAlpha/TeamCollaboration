package com.uww.messaging.display;

import java.sql.Timestamp;

/**
 * Created by horvste on 3/15/16.
 */
public class UserMessageDisplay {
    private String fromFirstName;
    private String toFirstName;
    private Timestamp timeOfMessage;
    private String message;

    public UserMessageDisplay(String message, String fromFirstName, String toFirstName, Timestamp timeOfMessage) {
        this.message = message;
        this.fromFirstName = fromFirstName;
        this.toFirstName = toFirstName;
        this.timeOfMessage = timeOfMessage;
    }

    public String getFromFirstName() {
        return fromFirstName;
    }

    public void setFromFirstName(String fromFirstName) {
        this.fromFirstName = fromFirstName;
    }

    public String getToFirstName() {
        return toFirstName;
    }

    public void setToFirstName(String toFirstName) {
        this.toFirstName = toFirstName;
    }

    public Timestamp getTimeOfMessage() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(Timestamp timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

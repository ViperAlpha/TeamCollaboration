package com.uww.messaging.display;

/**
 * Created by horvste on 3/19/16.
 */
public class Response {
    private boolean status;
    private String action;
    private String error;

    public Response(boolean status, String action, String error) {
        this.status = status;
        this.action = action;
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }

    public String getError() {
        return error;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setError(String error) {
        this.error = error;
    }
}

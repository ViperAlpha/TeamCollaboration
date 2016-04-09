package com.uww.messaging.display;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
public class IndividualMessageRequestBody {
	private int toUserId;
	private String message;


	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(final int toUserId) {
		this.toUserId = toUserId;
	}
}

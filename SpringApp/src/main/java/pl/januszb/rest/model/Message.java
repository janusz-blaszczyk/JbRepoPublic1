package pl.januszb.rest.model;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 4854890558013291677L;

	public Message() {
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package pl.januszb.rest.model;

import java.io.Serializable;
import java.util.Collection;

public class Message implements Serializable {

	private static final long serialVersionUID = 4854890558013291677L;

	public Message() {
	}

	private String message;

	private Message childMessage;
	
	Collection<String> collectionOfMessage;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message getChildMessage() {
		return childMessage;
	}

	public void setChildMessage(Message childMessage) {
		this.childMessage = childMessage;
	}

	public Collection<String> getCollectionOfMessage() {
		return collectionOfMessage;
	}

	public void setCollectionOfMessage(Collection<String> collectionOfMessage) {
		this.collectionOfMessage = collectionOfMessage;
	}

}

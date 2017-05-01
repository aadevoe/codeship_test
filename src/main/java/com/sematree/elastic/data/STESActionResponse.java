package com.sematree.elastic.data;

public class STESActionResponse {
	private boolean isSucceeded;
	private String errorMessage;
	private String payload;
	private Object document;
	
	public STESActionResponse(boolean isSucceeded, String errorMessage) {
		this.isSucceeded = isSucceeded;
		this.errorMessage = errorMessage;
	}
	
	public STESActionResponse(boolean isSucceeded, String errorMessage, Object document) {
		this.isSucceeded = isSucceeded;
		this.errorMessage = errorMessage;
		this.document = document;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String errorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	public Object getDocument() {
		return document;
	}
	
	public void setDocument(Object document) {
		this.document = document;
	}

}

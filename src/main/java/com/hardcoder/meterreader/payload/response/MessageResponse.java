package com.hardcoder.meterreader.payload.response;

public class MessageResponse {
	private long epochTime;
	private String message;

	public MessageResponse(long epochTime, String message) {
		this.epochTime = epochTime;
		this.message = message;
	}

	public long getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(long epochTime) {
		this.epochTime = epochTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

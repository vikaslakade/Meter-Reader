package com.hardcoder.meterreader.payload.response;

import com.hardcoder.meterreader.models.Body;

import java.util.List;

public class JwtResponse {

	private int status;
	private Body body;


	public JwtResponse(int status,Body body) {
		this.status=status;
		this.body=body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
}

package com.springboot.blog.payload;

//DTO to pass JWT(token) back to user as a response upon sign in
public class JwtAuthResponse {

	private String accessToken;
	private String tokenType;

	public JwtAuthResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}

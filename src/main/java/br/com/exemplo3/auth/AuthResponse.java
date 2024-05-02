package br.com.exemplo3.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	public AuthResponse setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public AuthResponse setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}
	
}

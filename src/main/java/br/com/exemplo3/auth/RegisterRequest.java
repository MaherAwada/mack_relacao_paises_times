package br.com.exemplo3.auth;

public class RegisterRequest {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}
	public RegisterRequest setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}
	public RegisterRequest setPassword(String password) {
		this.password = password;
		return this;
	}
	
}

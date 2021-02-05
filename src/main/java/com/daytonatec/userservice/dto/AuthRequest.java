package com.daytonatec.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AuthRequest {

    private String email;
    private String password;
	
    public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequest [email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

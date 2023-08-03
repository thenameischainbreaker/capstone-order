package com.capstone.capstoneorder.dto;

import com.capstone.capstoneorder.Admin;

public class UserRole {

	private boolean tokenValid;
	private Admin role;
	private String email;
	private int userId;
	
	
	public UserRole() {
		
	}
	
	public UserRole(boolean tokenValid, Admin role, String email, int userId) {
		super();
		this.tokenValid = tokenValid;
		this.role = role;
		this.email = email;
		this.userId = userId;
	}

	public boolean isTokenValid() {
		return tokenValid;
	}

	public void setTokenValid(boolean tokenValid) {
		this.tokenValid = tokenValid;
	}

	public Admin getRole() {
		return role;
	}

	public void setRole(Admin role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRole [tokenValid=" + tokenValid + ", role=" + role + ", email=" + email + ", userId=" + userId
				+ "]";
	}
	
	
	
	
	
	
}
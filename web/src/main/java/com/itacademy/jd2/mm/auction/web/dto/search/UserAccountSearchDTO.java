package com.itacademy.jd2.mm.auction.web.dto.search;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;

public class UserAccountSearchDTO {
	
	private String email;
	private Roles role;

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

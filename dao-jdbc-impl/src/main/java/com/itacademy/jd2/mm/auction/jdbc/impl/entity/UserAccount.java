package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private Integer role;
	private String email;
	private String password;
	private IPersonalData personalData;

	@Override
	public Integer getRole() {
		return role;
	}

	@Override
	public void setRole(final Integer role) {
		this.role = role;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAccount [role=" + role + ", email=" + email + ", password=" + password + ", getId()=" + getId()
				+ ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public IPersonalData getPersonalData() {
		return personalData;
	}

	@Override
	public void setPersonalData(IPersonalData personalData) {
		this.personalData = personalData;
	}

}

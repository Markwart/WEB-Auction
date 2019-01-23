package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import java.util.Set;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private Roles role;
	private String email;
	private String password;
	private IPersonalData personalData;

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

	@Override
	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public Roles getRole() {
		return role;
	}

	@Override
	public void setItems(Set<IItem> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<IItem> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

}

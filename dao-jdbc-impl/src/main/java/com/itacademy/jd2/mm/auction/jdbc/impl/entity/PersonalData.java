package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class PersonalData extends BaseEntity implements IPersonalData {

	private String userName, firstName, lastName, adress;
	private IUserAccount userAccount;

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getAdress() {
		return adress;
	}

	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "PersonalData [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", adress=" + adress + ", userAccount=" + userAccount + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}

}

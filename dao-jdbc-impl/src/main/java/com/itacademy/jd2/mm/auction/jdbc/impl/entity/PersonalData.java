package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class PersonalData extends BaseEntity implements IPersonalData {

	private String username, firstName, lastName, adress;
	private IUserAccount userAccount;

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
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
		return "PersonalData [userName=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", adress=" + adress + ", userAccount=" + userAccount + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public void setCity(String city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}
}

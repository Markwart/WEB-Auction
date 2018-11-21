package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IPersonalData extends IBaseEntity {
	
	String getUsername();
	
	void setUserName(String userName);
	
	String getFirstName();
	
	void setFirstName(String firstName);

	String getLastName();
	
	void setLastName(String lastName);
	
	String getAdress();
	
	void setAdress(String adress);
	
	IUserAccount getUserAccount();
	
	void setUserAccount(IUserAccount userAccount);
}
package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IUserAccount  extends IBaseEntity{

	Integer getRole();

	void setRole(Integer role);

	String getEmail();

	void setEmail(String email);
	
	String getPassword();
	
	void setPassword(String password);
	
	IPersonalData getPersonalData();
	
	void setPersonalData(IPersonalData data);
	
}

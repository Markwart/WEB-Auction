package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;

public interface IUserAccount  extends IBaseEntity{

	String getEmail();

	void setEmail(String email);
	
	String getPassword();
	
	void setPassword(String password);
	
	IPersonalData getPersonalData();
	
	void setPersonalData(IPersonalData data);

	void setRole(Roles role);

	Roles getRole();
}

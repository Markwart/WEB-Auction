package com.itacademy.jd2.mm.auction.daoapi.entity.table;


public interface IAdminCommunication extends IBaseEntity {

	String getTheme();

	void setTheme(String theme);

	String getText();

	void setText(String text);

	void setUserFrom(IUserAccount userFrom);

	IUserAccount getUserFrom();
	
}

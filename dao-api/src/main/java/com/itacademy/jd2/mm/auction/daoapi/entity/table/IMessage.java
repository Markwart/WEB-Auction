package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IMessage extends IBaseEntity {

	String getTheme();

	void setTheme(String theme);

	String getText();

	void setText(String text);

	IItem getItem();

	void setItem(IItem item);

	void setUserWhom(IUserAccount userWhom);

	IUserAccount getUserWhom();

	void setUserFrom(IUserAccount userFrom);

	IUserAccount getUserFrom();

}

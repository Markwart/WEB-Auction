package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IMessage extends IBaseEntity {

	String getTheme();

	void setTheme(String theme);

	String getText();

	void setText(String text);

	IUserAccount getUserAccountFrom();

	void setUserAccountFrom(IUserAccount userAccountFrom);

	IUserAccount getUserAccountWhom();

	void setUserAccountWhom(IUserAccount userAccountWhom);

	IItem getItem();

	void setItem(IItem item);

}

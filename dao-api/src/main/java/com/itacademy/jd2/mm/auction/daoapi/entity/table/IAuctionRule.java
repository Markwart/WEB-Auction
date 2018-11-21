package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IAuctionRule extends IBaseEntity{

	String getIndex();

	void setIndex(String index);

	String getTheme();

	void setTheme(String theme);

	String getText();

	void setText(String text);
}

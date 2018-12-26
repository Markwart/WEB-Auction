package com.itacademy.jd2.mm.auction.daoapi.entity.table;

public interface IFeedback extends IBaseEntity {

	Integer getCommunication();

	void setCommunication(Integer communication);
	
	Integer getShippingTime();
	
	void setShippingTime(Integer shippingTime);
	
	Integer getShippingCharges();
	
	void setShippingCharges(Integer shippingCharges);
	
	Integer getItemDescription();
	
	void setItemDescription(Integer itemDescription);
	
	String getComment();
	
	void setComment(String comment);

	IItem getItem();
	
	void setItem(IItem item);

	void setUserWhom(IUserAccount userWhom);

	IUserAccount getUserWhom();

	void setUserFrom(IUserAccount userFrom);

	IUserAccount getUserFrom();

}

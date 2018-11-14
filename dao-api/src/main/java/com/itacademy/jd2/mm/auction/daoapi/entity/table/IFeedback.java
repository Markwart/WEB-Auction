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

	IUserAccount getUserAccountFrom();

	void setUserAccountFrom(IUserAccount userAccountFrom);

	IUserAccount getUserAccountWhom();

	void setUserAccountWhom(IUserAccount userAccountWhom);
	
	IItem getItem();
	
	void setItem(IItem item);

}

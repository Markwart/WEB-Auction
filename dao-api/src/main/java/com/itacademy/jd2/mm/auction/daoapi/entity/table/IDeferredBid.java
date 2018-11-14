package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import java.math.BigDecimal;

public interface IDeferredBid extends IBaseEntity {

	BigDecimal getPriceBid();

	void setPriceBid(BigDecimal priceBid);

	String getStatusBid();

	void setStatusBid(String statusBid);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	IItem getItem();

	void setItem(IItem item);
}

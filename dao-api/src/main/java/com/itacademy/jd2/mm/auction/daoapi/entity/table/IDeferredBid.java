package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import java.math.BigDecimal;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;

public interface IDeferredBid extends IBaseEntity {

	BigDecimal getPriceBid();

	void setPriceBid(BigDecimal priceBid);

	IItem getItem();

	void setItem(IItem item);

	void setUserBid(IUserAccount userBid);

	IUserAccount getUserBid();

	void setStatusBid(StatusBid statusBid);

	StatusBid getStatusBid();
}

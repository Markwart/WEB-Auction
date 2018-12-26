package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class Bid extends BaseEntity implements IBid {

	private BigDecimal priceBid;
	private StatusBid statusBid;
	private IUserAccount userBid;
	private IItem item;

	@Override
	public BigDecimal getPriceBid() {
		return priceBid;
	}

	@Override
	public void setPriceBid(final BigDecimal priceBid) {
		this.priceBid = priceBid;
	}

	@Override
	public IItem getItem() {
		return item;
	}

	@Override
	public void setItem(IItem item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Bid [priceBid=" + priceBid + ", statusBid=" + statusBid + ", userAccount=" + userBid + ", item=" + item
				+ ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public void setUserBid(IUserAccount userBid) {
		this.userBid = userBid;
	}

	@Override
	public IUserAccount getUserBid() {
		return userBid;
	}

	@Override
	public void setStatusBid(StatusBid statusBid) {
		this.statusBid = statusBid;
	}

	@Override
	public StatusBid getStatusBid() {
		return statusBid;
	}

}

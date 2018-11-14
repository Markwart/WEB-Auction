package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class DeferredBid extends BaseEntity implements IDeferredBid {

	private BigDecimal priceBid;
	private String statusBid;
	private IUserAccount userAccount;
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
	public String getStatusBid() {
		return statusBid;
	}

	@Override
	public void setStatusBid(final String statusBid) {
		this.statusBid = statusBid;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(final IUserAccount userAccount) {
		this.userAccount = userAccount;
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
		return "DeferredBid [priceBid=" + priceBid + ", statusBid=" + statusBid + ", userAccount=" + userAccount
				+ ", item=" + item + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}

}

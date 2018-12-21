package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class Bid extends BaseEntity implements IBid {

	@Column
	private BigDecimal priceBid;
	
	@Column
	private String statusBid;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
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
}

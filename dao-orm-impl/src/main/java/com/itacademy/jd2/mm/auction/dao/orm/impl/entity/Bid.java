package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class Bid extends BaseEntity implements IBid {

	@Column
	private BigDecimal priceBid;
	
	@Column
	@Enumerated(EnumType.STRING)
	private StatusBid statusBid;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userBid;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
	private IItem item;

	
	@Override
	public IUserAccount getUserBid() {
		return userBid;
	}

	@Override
	public void setUserBid(IUserAccount userBid) {
		this.userBid = userBid;
	}

	@Override
	public BigDecimal getPriceBid() {
		return priceBid;
	}

	@Override
	public void setPriceBid(final BigDecimal priceBid) {
		this.priceBid = priceBid;
	}

	@Override
	public StatusBid getStatusBid() {
		return statusBid;
	}

	@Override
	public void setStatusBid(StatusBid statusBid) {
		this.statusBid = statusBid;
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

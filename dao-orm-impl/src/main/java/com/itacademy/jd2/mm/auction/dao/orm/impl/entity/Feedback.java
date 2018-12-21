package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class Feedback extends BaseEntity implements IFeedback {

	@Column
	private Integer communication, shippingTime, shippingCharges, itemDescription;
	
	@Column
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccountFrom, userAccountWhom;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
	private IItem item;

	@Override
	public Integer getCommunication() {
		return communication;
	}

	@Override
	public void setCommunication(final Integer communication) {
		this.communication = communication;
	}

	@Override
	public Integer getShippingTime() {
		return shippingTime;
	}

	@Override
	public void setShippingTime(final Integer shippingTime) {
		this.shippingTime = shippingTime;
	}

	@Override
	public Integer getShippingCharges() {
		return shippingCharges;
	}

	@Override
	public void setShippingCharges(final Integer shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	@Override
	public Integer getItemDescription() {
		return itemDescription;
	}

	@Override
	public void setItemDescription(final Integer itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Override
	public IUserAccount getUserAccountFrom() {
		return userAccountFrom;
	}

	@Override
	public void setUserAccountFrom(final IUserAccount userAccountFrom) {
		this.userAccountFrom = userAccountFrom;
	}

	@Override
	public IUserAccount getUserAccountWhom() {
		return userAccountWhom;
	}

	@Override
	public void setUserAccountWhom(final IUserAccount userAccountWhom) {
		this.userAccountWhom = userAccountWhom;
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

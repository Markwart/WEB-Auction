package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class Feedback extends BaseEntity implements IFeedback {

	private Integer communication, shippingTime, shippingCharges, itemDescription;
	private String comment;
	private IUserAccount userFrom, userWhom;
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
	public IItem getItem() {
		return item;
	}

	@Override
	public void setItem(IItem item) {
		this.item = item;
	}

	@Override
	public IUserAccount getUserFrom() {
		return userFrom;
	}

	@Override
	public void setUserFrom(IUserAccount userFrom) {
		this.userFrom = userFrom;
	}

	@Override
	public IUserAccount getUserWhom() {
		return userWhom;
	}

	@Override
	public void setUserWhom(IUserAccount userWhom) {
		this.userWhom = userWhom;
	}

}

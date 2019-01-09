package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;

public class AuctionDuration extends BaseEntity implements IAuctionDuration {

	private Integer day;

	@Override
	public Integer getDay() {
		return day;
	}

	@Override
	public void setDay(final Integer day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "AuctionDuration [day=" + day + ", getId()=" + getId() + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}

}

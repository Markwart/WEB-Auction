package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;

public class AuctionDuration extends BaseEntity implements IAuctionDuration {

	private Integer min;

	@Override
	public Integer getMin() {
		return min;
	}

	@Override
	public void setMin(final Integer min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "AuctionDuration [min=" + min + ", getId()=" + getId() + ", getCreated()=" + getCreated()
				+ ", getUpdated()=" + getUpdated() + "]";
	}

}

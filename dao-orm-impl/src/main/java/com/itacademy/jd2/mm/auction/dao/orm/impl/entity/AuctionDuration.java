package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;

@Entity
public class AuctionDuration extends BaseEntity implements IAuctionDuration {

	@Column
	private Integer min;

	@Override
	public Integer getMin() {
		return min;
	}

	@Override
	public void setMin(final Integer min) {
		this.min = min;
	}
}

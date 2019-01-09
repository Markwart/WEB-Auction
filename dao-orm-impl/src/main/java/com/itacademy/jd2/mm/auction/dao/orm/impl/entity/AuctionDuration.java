package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;

@Entity
public class AuctionDuration extends BaseEntity implements IAuctionDuration {

	@Column
	private Integer day;

	@Override
	public Integer getDay() {
		return day;
	}

	@Override
	public void setDay(final Integer day) {
		this.day = day;
	}
}

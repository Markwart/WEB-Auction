package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;

@Entity
public class CountryOrigin extends BaseEntity implements ICountryOrigin {

	@Column
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
}

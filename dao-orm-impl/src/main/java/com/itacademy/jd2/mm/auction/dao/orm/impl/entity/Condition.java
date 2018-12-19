package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;

@Entity
public class Condition extends BaseEntity implements ICondition {

	@Column
	private String name;

	public Condition(String name, Integer id) {
		super();
		setId(id);
		this.name = name;
	}

	public Condition() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
}

package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;

@Entity
public class Category extends BaseEntity implements ICategory {
	
	@Column
	private String name;
	
	public Category(String name, Integer id) {
		super();
		setId(id);
		this.name = name;
	}

	public Category() {
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

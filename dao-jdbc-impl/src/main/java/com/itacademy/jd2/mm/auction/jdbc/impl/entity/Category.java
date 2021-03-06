package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;

public class Category extends BaseEntity implements ICategory {
	
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()="
				+ getUpdated() + "]";
	}

}

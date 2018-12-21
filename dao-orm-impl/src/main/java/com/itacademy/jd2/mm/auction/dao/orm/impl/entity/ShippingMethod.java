package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;

@Entity
public class ShippingMethod extends BaseEntity implements IShippingMethod {

	@Column
	private String name;

	@Column
	private BigDecimal cost;

	@Column
	private String deliveryTime;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(final String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
}

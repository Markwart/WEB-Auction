package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;

public class ShippingMethod extends BaseEntity implements IShippingMethod{
	
	private String name;
	private BigDecimal cost;
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

	@Override
	public String toString() {
		return "ShippingMethod [name=" + name + ", cost=" + cost + ", deliveryTime=" + deliveryTime + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}

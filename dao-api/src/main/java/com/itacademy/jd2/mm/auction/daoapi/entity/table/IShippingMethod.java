package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import java.math.BigDecimal;

public interface IShippingMethod extends IBaseEntity {

	String getName();

	void setName(String name);

	BigDecimal getCost();

	void setCost(BigDecimal cost);

	String getDeliveryTime();

	void setDeliveryTime(String deliveryTime);
}

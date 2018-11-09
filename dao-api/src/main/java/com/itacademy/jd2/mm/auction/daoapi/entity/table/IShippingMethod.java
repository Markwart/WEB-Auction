package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

public interface IShippingMethod extends IBaseEntity {

	String getName();

	void setName(String name);

	BigDecimal getCost();

	void setCost(BigDecimal cost);

	Date getDeliveryTime();

	void setDeliveryTime(Date deliveryTime);
}

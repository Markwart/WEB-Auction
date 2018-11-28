package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;

public interface IShippingMethodDao extends IDao<IShippingMethod, Integer> {

	List<IShippingMethod> find(ShippingMethodFilter filter);

	long getCount(ShippingMethodFilter filter);
}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;

public interface IShippingMethodService {

	IShippingMethod get(Integer id);

	List<IShippingMethod> getAll();

	@Transactional
	void save(IShippingMethod entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IShippingMethod createEntity();
	
	List<IShippingMethod> find(ShippingMethodFilter filter);

    long getCount(ShippingMethodFilter filter);
}

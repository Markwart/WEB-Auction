package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;

public interface IShippingMethodService {

	IShippingMethod get(Integer id);

	List<IShippingMethod> getAll();

	void save(IShippingMethod entity);

	void delete(Integer id);

	void deleteAll();

	IShippingMethod createEntity();
}

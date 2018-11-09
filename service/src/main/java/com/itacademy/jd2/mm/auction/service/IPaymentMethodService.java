package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;

public interface IPaymentMethodService {
	
	IPaymentMethod get(Integer id);

	List<IPaymentMethod> getAll();

	void save(IPaymentMethod entity);

	void delete(Integer id);

	void deleteAll();

	IPaymentMethod createEntity();

}

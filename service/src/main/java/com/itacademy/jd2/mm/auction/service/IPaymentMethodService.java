package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.PaymentMethodFilter;

public interface IPaymentMethodService {
	
	IPaymentMethod get(Integer id);

	List<IPaymentMethod> getAll();

	@Transactional
	void save(IPaymentMethod entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPaymentMethod createEntity();

	List<IPaymentMethod> find(PaymentMethodFilter filter);

    long getCount(PaymentMethodFilter filter);
}

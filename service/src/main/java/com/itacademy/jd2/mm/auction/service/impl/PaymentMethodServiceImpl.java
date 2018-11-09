package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.mm.auction.daoapi.IPaymentMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.jdbc.impl.PaymentMethodDaoImpl;
import com.itacademy.jd2.mm.auction.service.IPaymentMethodService;

public class PaymentMethodServiceImpl implements IPaymentMethodService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

	private IPaymentMethodDao dao = new PaymentMethodDaoImpl();

	@Override
	public IPaymentMethod get(final Integer id) {
		final IPaymentMethod entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IPaymentMethod> getAll() {
		final List<IPaymentMethod> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IPaymentMethod entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new payment_method created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("payment_method updated: {}", entity);
		}
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete payment_method by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all payment_methods");
		dao.deleteAll();
	}

	@Override
	public IPaymentMethod createEntity() {
		return dao.createEntity();
	}

}

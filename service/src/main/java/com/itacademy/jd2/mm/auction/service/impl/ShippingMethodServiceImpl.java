package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IShippingMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.service.IShippingMethodService;

@Service
public class ShippingMethodServiceImpl implements IShippingMethodService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

	private IShippingMethodDao dao;

	@Autowired
	public ShippingMethodServiceImpl(IShippingMethodDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IShippingMethod get(final Integer id) {
		final IShippingMethod entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IShippingMethod> getAll() {
		final List<IShippingMethod> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IShippingMethod entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new shipping_method created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("shipping_method updated: {}", entity);
		}		
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete shipping_method by id: {}", id);
		dao.delete(id);		
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all shipping_methods");
		dao.deleteAll();		
	}

	@Override
	public IShippingMethod createEntity() {
		return dao.createEntity();
	}

}

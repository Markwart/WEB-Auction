package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.ICountryOriginDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;

@Service
public class CountryOriginServiceImpl implements ICountryOriginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryOriginServiceImpl.class);

	private ICountryOriginDao dao;
	
	@Autowired
	public CountryOriginServiceImpl(ICountryOriginDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICountryOrigin get(final Integer id) {
		final ICountryOrigin entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICountryOrigin> getAll() {
		final List<ICountryOrigin> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final ICountryOrigin entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new country_origin created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("country_origin updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete coutry_origin by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all countries_origin");
		dao.deleteAll();
	}

	@Override
	public ICountryOrigin createEntity() {
		return dao.createEntity();
	}
}

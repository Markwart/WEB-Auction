package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itacademy.jd2.mm.auction.daoapi.IPersonalDataDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.service.IPersonalDataService;

public class PersonalDataServiceImpl implements IPersonalDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalDataServiceImpl.class);

	private IPersonalDataDao dao;

	@Autowired
	public PersonalDataServiceImpl(IPersonalDataDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPersonalData get(Integer id) {
		final IPersonalData entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IPersonalData> getAll() {
		final List<IPersonalData> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IPersonalData entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new personal_date created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("personal_date updated: {}", entity);
		}	
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete personal_date by id: {}", id);
		dao.delete(id);	
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all personal_date");
		dao.deleteAll();
	}

	@Override
	public IPersonalData createEntity() {
		return dao.createEntity();
	}

}

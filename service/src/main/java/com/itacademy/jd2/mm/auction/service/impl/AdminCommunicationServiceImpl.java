package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IAdminCommunicationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;
import com.itacademy.jd2.mm.auction.service.IAdminCommunicationService;

@Service
public class AdminCommunicationServiceImpl implements IAdminCommunicationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminCommunicationServiceImpl.class);

	private IAdminCommunicationDao dao;

	@Autowired
	public AdminCommunicationServiceImpl(IAdminCommunicationDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IAdminCommunication get(final Integer id) {
		final IAdminCommunication entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IAdminCommunication> getAll() {
		final List<IAdminCommunication> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IAdminCommunication entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new admin_communication created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("admin_communication updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete admin_communication by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all admin_communications");
		dao.deleteAll();
	}

	@Override
	public IAdminCommunication createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IAdminCommunication> find(AdminCommunicationFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(AdminCommunicationFilter filter) {
		return dao.getCount(filter);
	}

}

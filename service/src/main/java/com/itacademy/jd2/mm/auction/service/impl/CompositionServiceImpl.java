package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.ICompositionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;
import com.itacademy.jd2.mm.auction.service.ICompositionService;

@Service
public class CompositionServiceImpl implements ICompositionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompositionServiceImpl.class);

	private ICompositionDao dao;
	
	@Autowired
	public CompositionServiceImpl(ICompositionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IComposition createEntity() {
		return dao.createEntity();
	}
	
	@Override
	public IComposition get(final Integer id) {
		final IComposition entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IComposition> getAll() {
		final List<IComposition> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IComposition entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new composition created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("composition updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete composition by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all compositions");
		dao.deleteAll();
	}

	@Override
	public List<IComposition> find(CompositionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CompositionFilter filter) {
		return dao.getCount(filter);
	}

}

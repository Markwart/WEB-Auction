package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IConditionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;
import com.itacademy.jd2.mm.auction.service.IConditionService;

@Service
public class ConditionServiceImpl implements IConditionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConditionServiceImpl.class);

	private IConditionDao dao;

	@Autowired
	public ConditionServiceImpl(IConditionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ICondition createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICondition entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.debug("new condition created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("condition updated: {}", entity);
		}
	}

	@Override
	public ICondition get(final Integer id) {
		final ICondition entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete condition by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all conditions");
		dao.deleteAll();
	}

	@Override
	public List<ICondition> getAll() {
		final List<ICondition> all = dao.selectAll();
		return all;
	}

	/*
	 * @Override public void save(ICondition... entities) { Date modified = new
	 * Date(); for (ICondition iCondition : entities) {
	 * 
	 * iCondition.setUpdated(modified); iCondition.setCreated(modified);
	 * 
	 * }
	 * 
	 * dao.save(entities); }
	 */

	@Override
	public List<ICondition> find(ConditionFilter filter) {
		return dao.find(filter);
	}

	/*@Override
	public long getCount(ConditionFilter filter) {
		return dao.getCount(filter);
	}
*/
}

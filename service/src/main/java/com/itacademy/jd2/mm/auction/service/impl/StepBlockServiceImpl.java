package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IStepBlockDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;
import com.itacademy.jd2.mm.auction.service.IStepBlockService;

@Service
public class StepBlockServiceImpl implements IStepBlockService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StepBlockServiceImpl.class);

	private IStepBlockDao dao;

	@Autowired
	public StepBlockServiceImpl(IStepBlockDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IStepBlock get(final Integer id) {
		final IStepBlock entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IStepBlock> getAll() {
		final List<IStepBlock> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IStepBlock entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new step_block created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("step_block updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete step_block by id: {}", id);
		dao.delete(id);	
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all step_blocks");
		dao.deleteAll();
	}

	@Override
	public IStepBlock createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IStepBlock> find(StepBlockFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(StepBlockFilter filter) {
		return dao.getCount(filter);
	}

}

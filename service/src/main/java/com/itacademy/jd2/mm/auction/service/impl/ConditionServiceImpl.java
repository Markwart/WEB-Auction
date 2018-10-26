package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.IConditionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.model.ICondition;
import com.itacademy.jd2.mm.auction.jdbc.impl.ConditionDaoImpl;
import com.itacademy.jd2.mm.auction.service.IConditionService;

public class ConditionServiceImpl implements IConditionService {

	private IConditionDao dao = new ConditionDaoImpl();

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
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ICondition get(final Integer id) {
		final ICondition entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ICondition> getAll() {
		final List<ICondition> all = dao.selectAll();
		return all;
	}

}

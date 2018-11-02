package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.mm.auction.daoapi.ICompositionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.jdbc.impl.CompositionDaoImpl;
import com.itacademy.jd2.mm.auction.service.ICompositionService;

public class CompositionServiceImpl implements ICompositionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompositionServiceImpl.class);

	private ICompositionDao dao = new CompositionDaoImpl();

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
	public void save(IComposition entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			LOGGER.info("new composition created: {}", entity);
			entity.setCreated(modefeOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("composition updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public void save(IComposition... entities) {
		Date modified = new Date();
		for (IComposition iComposition : entities) {
			
			iComposition.setUpdated(modified);
			iComposition.setCreated(modified);

		}
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all compositions");
		dao.deleteAll();
	}

	@Override
	public IComposition createEntity() {
		return dao.createEntity();
	}

}

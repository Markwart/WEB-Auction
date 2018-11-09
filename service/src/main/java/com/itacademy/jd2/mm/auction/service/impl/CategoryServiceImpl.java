package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.mm.auction.daoapi.ICategoryDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.jdbc.impl.CategoryDaoImpl;
import com.itacademy.jd2.mm.auction.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	private ICategoryDao dao = new CategoryDaoImpl();

	@Override
	public ICategory get(final Integer id) {
		final ICategory entity = dao.get(id);
		return entity;
	}

	@Override
	public List<ICategory> getAll() {
		final List<ICategory> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final ICategory entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new category created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("category updated: {}", entity);
		}		
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete category by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all categories");	
		dao.deleteAll();
	}

	@Override
	public ICategory createEntity() {
		return dao.createEntity();
	}

}

package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;
import com.itacademy.jd2.mm.auction.service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	private IFeedbackDao dao;

	@Autowired
	public FeedbackServiceImpl(IFeedbackDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IFeedback get(final Integer id) {
		final IFeedback entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IFeedback> getAll() {
		final List<IFeedback> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IFeedback entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new feedback created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("feedback updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete feedback by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all feedback");
		dao.deleteAll();
	}

	@Override
	public IFeedback createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IFeedback> find(FeedbackFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(FeedbackFilter filter) {
		return dao.getCount(filter);
	}
	
	@Override
    public IFeedback getFullInfo(Integer id) {
        return dao.getFullInfo(id);
    }
}

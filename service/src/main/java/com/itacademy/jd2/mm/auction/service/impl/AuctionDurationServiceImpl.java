package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IAuctionDurationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.service.IAuctionDurationService;

@Service
public class AuctionDurationServiceImpl implements IAuctionDurationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuctionDurationServiceImpl.class);

	private IAuctionDurationDao dao;

	@Autowired
	public AuctionDurationServiceImpl(IAuctionDurationDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IAuctionDuration get(final Integer id) {
		final IAuctionDuration entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IAuctionDuration> getAll() {
		final List<IAuctionDuration> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IAuctionDuration entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new auction_duration created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("auction_duration updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete auction_duration by id: {}", id);
		dao.delete(id);	
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all auction_duration");
		dao.deleteAll();
	}

	@Override
	public IAuctionDuration createEntity() {
		return dao.createEntity();
	}

}

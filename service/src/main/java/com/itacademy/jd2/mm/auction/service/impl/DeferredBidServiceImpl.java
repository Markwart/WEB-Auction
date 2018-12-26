package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;
import com.itacademy.jd2.mm.auction.service.IDeferredBidService;

@Service
public class DeferredBidServiceImpl implements IDeferredBidService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeferredBidServiceImpl.class);
	
	private IDeferredBidDao dao;
	
	@Autowired
	public DeferredBidServiceImpl(IDeferredBidDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IDeferredBid get(final Integer id) {
		final IDeferredBid entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IDeferredBid> getAll() {
		final List<IDeferredBid> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IDeferredBid entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new deferred_bid created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("deferred_bid updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete deferred_bid by id: {}", id);
		dao.delete(id);		
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all deferred_bids");
		dao.deleteAll();		
	}

	@Override
	public IDeferredBid createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IDeferredBid> find(DeferredBidFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(DeferredBidFilter filter) {
		return dao.getCount(filter);
	}

	@Override
    public IDeferredBid getFullInfo(Integer id) {
        return dao.getFullInfo(id);
    }
}

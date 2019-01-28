package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;
import com.itacademy.jd2.mm.auction.service.IBidService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;

@Service
public class BidServiceImpl implements IBidService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);

	private IBidDao dao;

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IItemService itemService;

	@Autowired
	public BidServiceImpl(IBidDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IBid get(final Integer id) {
		final IBid entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IBid> getAll() {
		final List<IBid> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IBid entity, Integer loggedUserId, Integer itemId) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);

			if (loggedUserId != null) {
				entity.setUserBid(userAccountService.get(loggedUserId));
				entity.setItem(itemService.get(itemId));
				entity.setStatusBid(StatusBid.made);
			}
			dao.insert(entity);
			LOGGER.debug("new bid created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("bid updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete bid by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all bids");
		dao.deleteAll();
	}

	@Override
	public IBid createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IBid> find(BidFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(BidFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public long getCountItemBids(BidFilter filter) {
		return dao.getCountItemBids(filter);
	}

	@Override
	public IBid getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<IBid> getLatestBidByItem(Integer id) {
		return dao.getLatestBidByItem(id);
	}
}

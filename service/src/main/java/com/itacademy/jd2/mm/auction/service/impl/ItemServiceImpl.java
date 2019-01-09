package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;
import com.itacademy.jd2.mm.auction.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private IItemDao dao;
	private IBidDao bidDao;
	private IDeferredBidDao deferredBidDao;
	private IMessageDao messageDao;
	private IFeedbackDao feedbackDao;

	@Autowired
	public ItemServiceImpl(IItemDao dao, IBidDao bidDao, IDeferredBidDao deferredBidDao, IMessageDao messageDao,
			IFeedbackDao feedbackDao) {
		super();
		this.dao = dao;
		this.bidDao = bidDao;
		this.deferredBidDao = deferredBidDao;
		this.messageDao = messageDao;
		this.feedbackDao = feedbackDao;
	}

	@Override
	public IItem get(Integer id) {
		final IItem entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IItem> getAll() {
		final List<IItem> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IItem entity) {
		/* final LocalDate now = LocalDate.now(); */
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			entity.setStatusAuction(StatusAuction.OPEN);
			//entity.setAuctionEnd(new Date(modefeOn.getTime() + (1000 * 60 * 60 * 24 * entity.getDuration().getDay())));
			//entity.setAuctionEnd(modefeOn);
			LOGGER.debug("new item created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("item updated: {}", entity);
		}
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("delete item by id: {}", id);
		deleteRelatedEntities(id);
		dao.delete(id);
	}

	private void deleteRelatedEntities(final Integer id) {

		for (IBid bid : bidDao.findRelatedBidsByItem(id)) {
			if (bid.getItem().getId().equals(id)) {
				bidDao.delete(bid.getId());
			}
		}
		for (IDeferredBid deferredBid : deferredBidDao.findRelatedDeferredBidsByItem(id)) {
			if (deferredBid.getItem().getId().equals(id)) {
				deferredBidDao.delete(deferredBid.getId());
			}
		}
		for (IFeedback feedback : feedbackDao.findRelatedFeedbackByItem(id)) {
			if (feedback.getItem().getId().equals(id)) {
				feedbackDao.delete(feedback.getId());
			}
		}
		for (IMessage message : messageDao.findRelatedMessagesByItem(id)) {
			if (message.getItem().getId().equals(id)) {
				messageDao.delete(message.getId());
			}
		}
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all items");
		dao.deleteAll();
	}

	@Override
	public IItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IItem> find(ItemFilter filter, Integer id) {
		return dao.find(filter, id);
	}

	@Override
	public long getCount(ItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IItem getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public List<IItem> search(String text) {
		return dao.search(text);
	}
}

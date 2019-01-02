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
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
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
		
		for (IBid bid : bidDao.selectAll()) {
			if (bid.getUserBid().getId().equals(id)) {
				bidDao.delete(bid.getId());
			}
		}
		for (IDeferredBid deferredBid : deferredBidDao.selectAll()) {
			if (deferredBid.getUserBid().getId().equals(id)) {
				deferredBidDao.delete(deferredBid.getId());
			}
		}
		for (IFeedback feedback : feedbackDao.selectAll()) {
			if (feedback.getUserFrom().getId().equals(id) || feedback.getUserWhom().getId().equals(id)) {
				feedbackDao.delete(feedback.getId());
			}
		}
		for (IMessage message : messageDao.selectAll()) {
			if (message.getUserFrom().getId().equals(id) || message.getUserWhom().getId().equals(id)) {
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
	public List<IItem> find(ItemFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IItem getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}
	
	/*@Override
    public List<IItem> search(String text) {
        return dao.search(text);
    }*/
}

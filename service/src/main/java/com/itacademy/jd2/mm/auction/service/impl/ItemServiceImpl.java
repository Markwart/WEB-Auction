package com.itacademy.jd2.mm.auction.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;
import com.itacademy.jd2.mm.auction.service.IAuctionDurationService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;

@Service
public class ItemServiceImpl implements IItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
	public static final String FILE_FOLDER = "d:\\JavaMark\\web-auction\\G-JD1-06-13_mmatusevich\\doc\\image\\";

	private IItemDao dao;
	private IBidDao bidDao;
	private IDeferredBidDao deferredBidDao;
	private IMessageDao messageDao;
	private IFeedbackDao feedbackDao;

	@Autowired
	private IAuctionDurationService auctionDurationService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IItemService itemService;

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
	public void save(IItem entity, Integer id, String uuid, MultipartFile file) throws IOException {
		final Date now = new Date();
		entity.setUpdated(now);
		if (entity.getId() == null) {
			entity.setCreated(now);
			entity.setStatusAuction(StatusAuction.OPEN);
			entity.setSeller(userAccountService.get(id));

			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.DAY_OF_YEAR, auctionDurationService.get(entity.getDuration().getId()).getDay());
			entity.setAuctionEnd(c.getTime());

			dao.insert(entity);

			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(), StandardCopyOption.REPLACE_EXISTING);

			LOGGER.debug("new item created: {}", entity);
		} else {

			if (!userAccountService.get(id).getRole().equals(Roles.admin)) {
				entity.setStatusAuction(itemService.get(entity.getId()).getStatusAuction());
				entity.setSeller(userAccountService.get(id));
				entity.setAuctionEnd(itemService.get(entity.getId()).getAuctionEnd());
			}
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
	public List<IItem> findInIndex(String name) {
		List<IItem> findInIndex = dao.findInIndex(name);
		List<IItem> result = new ArrayList<>();
		for (IItem iItem : findInIndex) {
			result.add(dao.getFullInfo(iItem.getId()));
		}
		return result;
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
}

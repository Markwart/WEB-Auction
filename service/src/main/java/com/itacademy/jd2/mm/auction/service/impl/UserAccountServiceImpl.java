package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IAdminCommunicationDao;
import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.IPersonalDataDao;
import com.itacademy.jd2.mm.auction.daoapi.IUserAccountDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.service.utils.PasswordUtils;
import com.itacademy.jd2.mm.auction.service.utils.SendMailTLS;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private IUserAccountDao dao;
	private IPersonalDataDao personalDataDao;
	private IAdminCommunicationDao adminCommunicationDao;
	private IFeedbackDao feedbackDao;
	private IMessageDao messageDao;
	private IBidDao bidDao;
	private IDeferredBidDao deferredBidDao;
	private IItemDao itemDao;
	
	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao, IPersonalDataDao personalDataDao,
			IAdminCommunicationDao adminCommunicationDao, IFeedbackDao feedbackDao, IMessageDao messageDao,
			IBidDao bidDao, IDeferredBidDao deferredBidDao, IItemDao itemDao) {
		super();
		this.dao = dao;
		this.personalDataDao = personalDataDao;
		this.adminCommunicationDao = adminCommunicationDao;
		this.feedbackDao = feedbackDao;
		this.messageDao = messageDao;
		this.bidDao = bidDao;
		this.deferredBidDao = deferredBidDao;
		this.itemDao = itemDao;
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete user_account by id: {}", id);
		 final IUserAccount userAccount = dao.get(id);
	        userAccount.getItems().clear();
	        dao.update(userAccount);
		deleteRelatedEntities(id);
		personalDataDao.delete(id);
		dao.delete(id);
	}

	private void deleteRelatedEntities(final Integer id) {

		for (IAdminCommunication adminCommunication : adminCommunicationDao.findRelatedAdminCommunicationByUser(id)) {
			if (adminCommunication.getUserFrom().getId().equals(id)) {
				adminCommunicationDao.delete(adminCommunication.getId());
			}
		}
		for (IItem item : itemDao.findRelatedItemsBySeller(id)) {
			if (item.getSeller().getId().equals(id)) {
				itemDao.delete(item.getId());
			}
		}
		for (IBid bid : bidDao.findRelatedBidsByUser(id)) {
			if (bid.getUserBid().getId().equals(id)) {
				bidDao.delete(bid.getId());
			}
		}
		for (IDeferredBid deferredBid : deferredBidDao.findRelatedDeferredBidsByUser(id)) {
			if (deferredBid.getUserBid().getId().equals(id)) {
				deferredBidDao.delete(deferredBid.getId());
			}
		}
		for (IFeedback feedback : feedbackDao.findRelatedFeedbackByUser(id)) {
			if (feedback.getUserFrom().getId().equals(id) | feedback.getUserWhom().getId().equals(id)) {
				feedbackDao.delete(feedback.getId());
			}
		}
		for (IMessage message : messageDao.findRelatedMessagesByUser(id)) {
			if (message.getUserFrom().getId().equals(id) | message.getUserWhom().getId().equals(id)) {
				messageDao.delete(message.getId());
			}
		}
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all user_accounts");
		personalDataDao.deleteAll();
		dao.deleteAll();
	}

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IPersonalData createPersonalDataEntity() {
		return personalDataDao.createEntity();
	}

	@Override
	public IUserAccount getPersonalData(Integer id) {
		final IUserAccount entity = dao.getPersonalData(id);
		return entity;
	}

	@Override
	public void save(IUserAccount userAccount, IPersonalData personalData) {
		final Date modifiedDate = new Date();
		userAccount.setUpdated(modifiedDate);
		personalData.setUpdated(modifiedDate);

		if (userAccount.getId() == null) {
			userAccount.setCreated(modifiedDate);
			userAccount.setPassword(PasswordUtils.generateSecurePassword(userAccount.getPassword()));
			userAccount.setRole(Roles.user);
			dao.insert(userAccount);
			SendMailTLS.sendMail();

			personalData.setId(userAccount.getId());
			personalData.setCreated(modifiedDate);
			personalData.setUserAccount(userAccount);
			personalDataDao.insert(personalData);
			
			userAccount.setPersonalData(personalData);
		} else {
			userAccount.setPassword(PasswordUtils.generateSecurePassword(userAccount.getPassword()));
			dao.update(userAccount);
			personalDataDao.update(personalData);
		}
	}

	@Override
	public IUserAccount getUserByLogin(String username) {
		return dao.getUserByLogin(username);
	}

	@Override
	public void update(IUserAccount personalData) {
		dao.update(personalData);
		
	}
}

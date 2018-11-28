package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IUserAccountDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private IUserAccountDao dao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao) {
		super();
		this.dao = dao;
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
	public void save(final IUserAccount entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new user_account created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("user_account updated: {}", entity);
		}			
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete user_account by id: {}", id);
		dao.delete(id);			
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all user_accounts");
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
}

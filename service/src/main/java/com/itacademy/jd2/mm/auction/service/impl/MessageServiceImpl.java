package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;
import com.itacademy.jd2.mm.auction.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	private IMessageDao dao;

	@Autowired
	public MessageServiceImpl(IMessageDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IMessage get(final Integer id) {
		final IMessage entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IMessage> getAll() {
		final List<IMessage> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IMessage entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new message created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("message updated: {}", entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete message by id: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all messages");
		dao.deleteAll();
	}

	@Override
	public IMessage createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IMessage> find(MessageFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(MessageFilter filter) {
		return dao.getCount(filter);
	}

}

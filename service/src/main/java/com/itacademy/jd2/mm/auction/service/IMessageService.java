package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

public interface IMessageService {

	IMessage get(Integer id);

	List<IMessage> getAll();

	@Transactional
	void save(IMessage entity, Integer loggedUserId, Integer itemId);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IMessage createEntity();

	long getCount(MessageFilter filter);

	IMessage getFullInfo(Integer id);

	List<IMessage> find(MessageFilter filter);

}

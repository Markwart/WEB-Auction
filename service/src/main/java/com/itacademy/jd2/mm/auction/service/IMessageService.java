package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

public interface IMessageService {

	IMessage get(Integer id);

	List<IMessage> getAll();

	void save(IMessage entity);

	void delete(Integer id);

	void deleteAll();

	IMessage createEntity();
	
	List<IMessage> find(MessageFilter filter);

    long getCount(MessageFilter filter);
}

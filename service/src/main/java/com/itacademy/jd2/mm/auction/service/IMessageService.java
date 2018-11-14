package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;

public interface IMessageService {

	IMessage get(Integer id);

	List<IMessage> getAll();

	void save(IMessage entity);

	void delete(Integer id);

	void deleteAll();

	IMessage createEntity();
}

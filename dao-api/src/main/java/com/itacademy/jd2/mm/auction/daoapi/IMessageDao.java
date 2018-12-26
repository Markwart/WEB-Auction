package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

public interface IMessageDao extends IDao<IMessage, Integer> {

	List<IMessage> find(MessageFilter filter);

	long getCount(MessageFilter filter);

	IMessage getFullInfo(Integer id);
}

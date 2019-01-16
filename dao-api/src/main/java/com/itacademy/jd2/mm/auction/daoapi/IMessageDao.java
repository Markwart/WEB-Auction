package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

public interface IMessageDao extends IDao<IMessage, Integer> {

	long getCount(MessageFilter filter);

	IMessage getFullInfo(Integer id);

	List<IMessage> findRelatedMessagesByItem(Integer id);

	List<IMessage> findRelatedMessagesByUser(Integer id);

	List<IMessage> find(MessageFilter filter);
}

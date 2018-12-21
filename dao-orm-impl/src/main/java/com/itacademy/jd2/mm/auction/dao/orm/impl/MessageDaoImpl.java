package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Message;
import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

@Repository
public class MessageDaoImpl extends AbstractDaoImpl<IMessage, Integer> implements IMessageDao {

	protected MessageDaoImpl() {
		super(Message.class);
	}

	@Override
	public IMessage createEntity() {
		final Message message = new Message();
		return message;
	}

	@Override
	public List<IMessage> find(MessageFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(MessageFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

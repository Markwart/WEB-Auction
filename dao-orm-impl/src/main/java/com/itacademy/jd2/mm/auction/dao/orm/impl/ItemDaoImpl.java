package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item;
import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

@Repository
public class ItemDaoImpl extends AbstractDaoImpl<IItem, Integer> implements IItemDao {

	protected ItemDaoImpl() {
		super(Item.class);
	}

	@Override
	public IItem createEntity() {
		final Item item = new Item();
		return item;
	}

	@Override
	public List<IItem> find(ItemFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ItemFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

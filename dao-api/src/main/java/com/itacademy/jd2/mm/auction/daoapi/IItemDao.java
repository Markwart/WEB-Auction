package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

public interface IItemDao extends IDao<IItem, Integer> {

	long getCount(ItemFilter filter);

	IItem getFullInfo(Integer id);

	List<IItem> findInIndex(String text);

	List<IItem> findRelatedItemsBySeller(Integer id);

	List<IItem> find(ItemFilter filter, Integer id);

}

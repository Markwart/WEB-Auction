package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

public interface IItemService {
	
	IItem get(Integer id);

	List<IItem> getAll();

	void save(IItem entity);

	void delete(Integer id);

	void deleteAll();

	IItem createEntity();
	
	List<IItem> find(ItemFilter filter);

    long getCount(ItemFilter filter);

}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;

public interface IItemService {
	
	IItem get(Integer id);

	List<IItem> getAll();

	void save(IItem entity);

	void delete(Integer id);

	void deleteAll();

	IItem createEntity();

}

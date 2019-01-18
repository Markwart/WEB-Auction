package com.itacademy.jd2.mm.auction.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

public interface IItemService {
	
	IItem get(Integer id);

	List<IItem> getAll();

	@Transactional
	void save(IItem entity, Integer id, String uuid, MultipartFile file) throws IOException;

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IItem createEntity();
	
	List<IItem> find(ItemFilter filter);
	
	@Transactional // because hibernate search is used. need to keep session opened
	List<IItem> findInIndex(String name);
	
    long getCount(ItemFilter filter);

	IItem getFullInfo(Integer id);


}

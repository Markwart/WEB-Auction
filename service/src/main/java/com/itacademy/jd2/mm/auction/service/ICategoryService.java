package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.filter.CategoryFilter;

public interface ICategoryService {
	
	ICategory get(Integer id);

	List<ICategory> getAll();

	@Transactional
	void save(ICategory entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICategory createEntity();

	List<ICategory> find(CategoryFilter filter);

    long getCount(CategoryFilter filter);
}

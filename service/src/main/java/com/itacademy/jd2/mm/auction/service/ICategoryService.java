package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.filter.CategoryFilter;

public interface ICategoryService {
	
	ICategory get(Integer id);

	List<ICategory> getAll();

	void save(ICategory entity);

	void delete(Integer id);

	void deleteAll();

	ICategory createEntity();

	List<ICategory> find(CategoryFilter filter);

    long getCount(CategoryFilter filter);
}

package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.filter.CategoryFilter;

public interface ICategoryDao extends IDao<ICategory, Integer> {

	List<ICategory> find(CategoryFilter filter);

	long getCount(CategoryFilter filter);
}

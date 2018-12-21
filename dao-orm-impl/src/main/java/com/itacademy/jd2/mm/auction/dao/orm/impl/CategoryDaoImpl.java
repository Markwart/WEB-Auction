package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Category;
import com.itacademy.jd2.mm.auction.daoapi.ICategoryDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.filter.CategoryFilter;

@Repository
public class CategoryDaoImpl extends AbstractDaoImpl<ICategory, Integer> implements ICategoryDao {

	protected CategoryDaoImpl() {
		super(Category.class);
	}

	@Override
	public ICategory createEntity() {
		final Category category = new Category();
		return category;
	}

	@Override
	public List<ICategory> find(CategoryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CategoryFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;

public interface ICompositionService {

	IComposition get(Integer id);

	List<IComposition> getAll();

	void save(IComposition entity);

	void delete(Integer id);

	void deleteAll();

	IComposition createEntity();
	
	List<IComposition> find(CompositionFilter filter);

    long getCount(CompositionFilter filter);
}

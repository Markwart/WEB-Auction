package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;

public interface ICompositionService {

	IComposition get(Integer id);

	List<IComposition> getAll();

	@Transactional
	void save(IComposition entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IComposition createEntity();
	
	List<IComposition> find(CompositionFilter filter);

    long getCount(CompositionFilter filter);
}

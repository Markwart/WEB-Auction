package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;

public interface IBidService {

	IBid get(Integer id);

	List<IBid> getAll();

	@Transactional
	void save(IBid entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IBid createEntity();
	
	List<IBid> find(BidFilter filter);

    long getCount(BidFilter filter);

	IBid getFullInfo(Integer id);
}

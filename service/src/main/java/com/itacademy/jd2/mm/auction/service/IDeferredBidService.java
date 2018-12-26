package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;

public interface IDeferredBidService {

	IDeferredBid get(Integer id);

	List<IDeferredBid> getAll();

	@Transactional
	void save(IDeferredBid entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IDeferredBid createEntity();
	
	List<IDeferredBid> find(DeferredBidFilter filter);

    long getCount(DeferredBidFilter filter);

	IDeferredBid getFullInfo(Integer id);
}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;

public interface IAuctionDurationService {
	
	IAuctionDuration get(Integer id);

	List<IAuctionDuration> getAll();

	@Transactional
	void save(IAuctionDuration entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IAuctionDuration createEntity();
	
	List<IAuctionDuration> find(AuctionDurationFilter filter);

    long getCount(AuctionDurationFilter filter);
}

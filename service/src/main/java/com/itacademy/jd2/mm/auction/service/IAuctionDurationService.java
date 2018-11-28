package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;

public interface IAuctionDurationService {
	
	IAuctionDuration get(Integer id);

	List<IAuctionDuration> getAll();

	void save(IAuctionDuration entity);

	void delete(Integer id);

	void deleteAll();

	IAuctionDuration createEntity();
	
	List<IAuctionDuration> find(AuctionDurationFilter filter);

    long getCount(AuctionDurationFilter filter);
}

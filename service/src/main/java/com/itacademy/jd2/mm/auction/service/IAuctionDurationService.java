package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;

public interface IAuctionDurationService {
	
	IAuctionDuration get(Integer id);

	List<IAuctionDuration> getAll();

	void save(IAuctionDuration entity);

	void delete(Integer id);

	void deleteAll();

	IAuctionDuration createEntity();
}

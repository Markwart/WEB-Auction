package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;

public interface IDeferredBidService {

	IDeferredBid get(Integer id);

	List<IDeferredBid> getAll();

	void save(IDeferredBid entity);

	void delete(Integer id);

	void deleteAll();

	IDeferredBid createEntity();
}

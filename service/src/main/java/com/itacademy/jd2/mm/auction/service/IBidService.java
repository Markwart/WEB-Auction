package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;

public interface IBidService {

	IBid get(Integer id);

	List<IBid> getAll();

	void save(IBid entity);

	void delete(Integer id);

	void deleteAll();

	IBid createEntity();
}

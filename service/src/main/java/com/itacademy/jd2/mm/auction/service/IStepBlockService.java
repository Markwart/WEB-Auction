package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;

public interface IStepBlockService {
	
	IStepBlock get(Integer id);

	List<IStepBlock> getAll();

	void save(IStepBlock entity);

	void delete(Integer id);

	void deleteAll();

	IStepBlock createEntity();

}

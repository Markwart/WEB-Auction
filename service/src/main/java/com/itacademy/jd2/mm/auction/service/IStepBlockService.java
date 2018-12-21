package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;

public interface IStepBlockService {
	
	IStepBlock get(Integer id);

	List<IStepBlock> getAll();

	@Transactional
	void save(IStepBlock entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IStepBlock createEntity();

	List<IStepBlock> find(StepBlockFilter filter);

    long getCount(StepBlockFilter filter);
}

package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.StepBlock;
import com.itacademy.jd2.mm.auction.daoapi.IStepBlockDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;

@Repository
public class StepBlockDaoImpl extends AbstractDaoImpl<IStepBlock, Integer> implements IStepBlockDao {

	protected StepBlockDaoImpl() {
		super(StepBlock.class);
	}

	@Override
	public IStepBlock createEntity() {
		final StepBlock stepBlock = new StepBlock();
		return stepBlock;
	}

	@Override
	public List<IStepBlock> find(StepBlockFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(StepBlockFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

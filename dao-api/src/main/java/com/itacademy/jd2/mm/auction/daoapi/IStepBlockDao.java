package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;

public interface IStepBlockDao extends IDao<IStepBlock, Integer> {

	List<IStepBlock> find(StepBlockFilter filter);

	long getCount(StepBlockFilter filter);
}

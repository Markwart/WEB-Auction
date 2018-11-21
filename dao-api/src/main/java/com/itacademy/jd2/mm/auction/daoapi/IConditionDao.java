package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;

public interface IConditionDao extends IDao<ICondition, Integer> {

	//void save(ICondition... entities); // skip
	
	List<ICondition> find(ConditionFilter filter);
}

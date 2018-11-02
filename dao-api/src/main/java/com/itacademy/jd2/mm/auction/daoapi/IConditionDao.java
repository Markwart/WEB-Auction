package com.itacademy.jd2.mm.auction.daoapi;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;

public interface IConditionDao extends IDao<ICondition, Integer> {

	void save(ICondition... entities); // skip

}

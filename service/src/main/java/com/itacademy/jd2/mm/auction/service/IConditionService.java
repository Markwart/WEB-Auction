package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;

public interface IConditionService {

	ICondition get(Integer id);

    List<ICondition> getAll();

    void save(ICondition entity);

    void save(ICondition... entity);// skip

    void delete(Integer id);

    void deleteAll();

    ICondition createEntity();

    //List<ICondition> find(ConditionFilter filter);// skip

    //long getCount(ConditionFilter filter);// skip

}

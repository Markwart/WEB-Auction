package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;

public interface IConditionService {

	ICondition get(Integer id);

    List<ICondition> getAll();

    @Transactional
    void save(ICondition entity);

    //void save(ICondition... entity);// skip

    @Transactional
    void delete(Integer id);

    @Transactional
    void deleteAll();

    ICondition createEntity();

    List<ICondition> find(ConditionFilter filter);

    long getCount(ConditionFilter filter);

}

package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;

public interface ICompositionDao extends IDao<IComposition, Integer> {

	List<IComposition> find(CompositionFilter filter);

	long getCount(CompositionFilter filter);
}

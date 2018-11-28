package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;

public interface ICountryOriginDao extends IDao<ICountryOrigin, Integer> {

	List<ICountryOrigin> find(CountryOriginFilter filter);

	long getCount(CountryOriginFilter filter);
}

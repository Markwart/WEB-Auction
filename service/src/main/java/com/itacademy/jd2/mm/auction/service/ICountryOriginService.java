package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;

public interface ICountryOriginService {

	ICountryOrigin get(Integer id);

	List<ICountryOrigin> getAll();

	void save(ICountryOrigin entity);

	void delete(Integer id);

	void deleteAll();

	ICountryOrigin createEntity();
	
	List<ICountryOrigin> find(CountryOriginFilter filter);

    long getCount(CountryOriginFilter filter);
}

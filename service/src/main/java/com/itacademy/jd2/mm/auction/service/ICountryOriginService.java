package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;

public interface ICountryOriginService {

	ICountryOrigin get(Integer id);

	List<ICountryOrigin> getAll();

	@Transactional
	void save(ICountryOrigin entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICountryOrigin createEntity();
	
	List<ICountryOrigin> find(CountryOriginFilter filter);

    long getCount(CountryOriginFilter filter);
}

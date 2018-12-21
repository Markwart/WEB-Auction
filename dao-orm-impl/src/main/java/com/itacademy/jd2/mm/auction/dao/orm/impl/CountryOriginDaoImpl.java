package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.CountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.ICountryOriginDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;

@Repository
public class CountryOriginDaoImpl extends AbstractDaoImpl<ICountryOrigin, Integer> implements ICountryOriginDao {

	protected CountryOriginDaoImpl() {
		super(CountryOrigin.class);
	}

	@Override
	public ICountryOrigin createEntity() {
		final CountryOrigin countryOrigin = new CountryOrigin();
		return countryOrigin;
	}

	@Override
	public List<ICountryOrigin> find(CountryOriginFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CountryOriginFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

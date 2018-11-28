package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.filter.PersonalDataFilter;

public interface IPersonalDataService {

	IPersonalData get(Integer id);

	List<IPersonalData> getAll();

	void save(IPersonalData entity);

	void delete(Integer id);

	void deleteAll();

	IPersonalData createEntity();
	
	List<IPersonalData> find(PersonalDataFilter filter);

    long getCount(PersonalDataFilter filter);
}

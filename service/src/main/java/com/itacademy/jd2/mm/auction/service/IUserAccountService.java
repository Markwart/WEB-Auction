package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

public interface IUserAccountService {

	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	void save(IUserAccount entity);

	void delete(Integer id);

	void deleteAll();

	IUserAccount createEntity();
	
	IPersonalData createPersonalDataEntity();
	
	IUserAccount getPersonalData(final Integer id);
	
	void save(IUserAccount entity, IPersonalData personalDataEntity);
	
	List<IUserAccount> find(UserAccountFilter filter);

    long getCount(UserAccountFilter filter);
}

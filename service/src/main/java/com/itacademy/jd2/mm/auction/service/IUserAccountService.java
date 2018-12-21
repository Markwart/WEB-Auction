package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

public interface IUserAccountService {

	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccount createEntity();
	
	IPersonalData createPersonalDataEntity();
	
	IUserAccount getPersonalData(final Integer id);
	
	@Transactional
	void save(IUserAccount entity, IPersonalData personalDataEntity);
	
	List<IUserAccount> find(UserAccountFilter filter);

    long getCount(UserAccountFilter filter);
}

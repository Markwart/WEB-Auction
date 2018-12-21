package com.itacademy.jd2.mm.auction.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.PersonalData;
import com.itacademy.jd2.mm.auction.daoapi.IPersonalDataDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;

@Repository
public class PersonalDataDaoImpl extends AbstractDaoImpl<IPersonalData, Integer> implements IPersonalDataDao {

	protected PersonalDataDaoImpl() {
		super(PersonalData.class);
	}

	@Override
	public IPersonalData createEntity() {
		final PersonalData personalData = new PersonalData();
		return personalData;
	}
}

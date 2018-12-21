package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.IAdminCommunicationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;

@Repository
public class AdminCommunicationDaoImpl extends AbstractDaoImpl<IAdminCommunication, Integer> implements IAdminCommunicationDao {

	protected AdminCommunicationDaoImpl() {
		super(AdminCommunication.class);
	}

	@Override
	public IAdminCommunication createEntity() {
		final AdminCommunication adminCommunication = new AdminCommunication();
		return adminCommunication;
	}

	@Override
	public List<IAdminCommunication> find(AdminCommunicationFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(AdminCommunicationFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;

public interface IAdminCommunicationService {

	IAdminCommunication get(Integer id);

	List<IAdminCommunication> getAll();

	@Transactional
	void save(IAdminCommunication entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IAdminCommunication createEntity();
	
	List<IAdminCommunication> find(AdminCommunicationFilter filter);

    long getCount(AdminCommunicationFilter filter);
}

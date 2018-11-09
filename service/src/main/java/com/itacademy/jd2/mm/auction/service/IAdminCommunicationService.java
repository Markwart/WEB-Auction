package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;

public interface IAdminCommunicationService {

	IAdminCommunication get(Integer id);

	List<IAdminCommunication> getAll();

	void save(IAdminCommunication entity);

	void delete(Integer id);

	void deleteAll();

	IAdminCommunication createEntity();
}

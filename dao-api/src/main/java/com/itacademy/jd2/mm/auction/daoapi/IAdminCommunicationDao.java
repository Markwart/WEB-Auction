package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;

public interface IAdminCommunicationDao extends IDao<IAdminCommunication, Integer> {

	List<IAdminCommunication> find(AdminCommunicationFilter filter);

	long getCount(AdminCommunicationFilter filter);

	IAdminCommunication getFullInfo(Integer id);

	List<IAdminCommunication> findRelatedAdminCommunicationByUser(Integer id);

}

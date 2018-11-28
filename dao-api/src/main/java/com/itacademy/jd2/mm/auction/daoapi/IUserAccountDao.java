package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);
}

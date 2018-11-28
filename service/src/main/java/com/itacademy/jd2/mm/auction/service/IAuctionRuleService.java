package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;

public interface IAuctionRuleService {
	
	IAuctionRule get(Integer id);

	List<IAuctionRule> getAll();

	void save(IAuctionRule entity);

	void delete(Integer id);

	void deleteAll();

	IAuctionRule createEntity();
	
	List<IAuctionRule> find(AuctionRuleFilter filter);

    long getCount(AuctionRuleFilter filter);
}

package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;

public interface IAuctionRuleService {
	
	IAuctionRule get(Integer id);

	List<IAuctionRule> getAll();

	@Transactional
	void save(IAuctionRule entity);

	@Transactional
	void delete(Integer id);
	
	@Transactional
	void deleteAll();

	IAuctionRule createEntity();
	
	List<IAuctionRule> find(AuctionRuleFilter filter);

    long getCount(AuctionRuleFilter filter);
}

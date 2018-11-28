package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;

public interface IAuctionRuleDao extends IDao<IAuctionRule, Integer> {

	List<IAuctionRule> find(AuctionRuleFilter filter);

	long getCount(AuctionRuleFilter filter);
}

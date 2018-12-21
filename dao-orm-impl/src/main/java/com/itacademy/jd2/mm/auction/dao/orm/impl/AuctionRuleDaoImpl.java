package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.IAuctionRuleDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;

@Repository
public class AuctionRuleDaoImpl extends AbstractDaoImpl<IAuctionRule, Integer> implements IAuctionRuleDao {

	protected AuctionRuleDaoImpl() {
		super(AuctionRule.class);
	}

	@Override
	public IAuctionRule createEntity() {
		final AuctionRule auctionRule = new AuctionRule();
		return auctionRule;
	}

	@Override
	public List<IAuctionRule> find(AuctionRuleFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(AuctionRuleFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

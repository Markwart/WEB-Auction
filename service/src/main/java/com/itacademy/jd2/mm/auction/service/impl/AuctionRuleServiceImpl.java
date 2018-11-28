package com.itacademy.jd2.mm.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.mm.auction.daoapi.IAuctionRuleDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;
import com.itacademy.jd2.mm.auction.service.IAuctionRuleService;

@Service
public class AuctionRuleServiceImpl implements IAuctionRuleService{

	private static final Logger LOGGER = LoggerFactory.getLogger(AuctionRuleServiceImpl.class);
	
	private IAuctionRuleDao dao;
	
	@Autowired
	public AuctionRuleServiceImpl(IAuctionRuleDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IAuctionRule get(final Integer id) {
		final IAuctionRule entity = dao.get(id);
		return entity;
	}

	@Override
	public List<IAuctionRule> getAll() {
		final List<IAuctionRule> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IAuctionRule entity) {
		final Date modefeOn = new Date();
		entity.setUpdated(modefeOn);
		if (entity.getId() == null) {
			entity.setCreated(modefeOn);
			dao.insert(entity);
			LOGGER.debug("new auction_rule created: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("auction_rule updated: {}", entity);
		}		
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete auction_rule by id: {}", id);
		dao.delete(id);			
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all auction_rules");
		dao.deleteAll();		
	}

	@Override
	public IAuctionRule createEntity() {
		return dao.createEntity();
	}

	@Override
	public List<IAuctionRule> find(AuctionRuleFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(AuctionRuleFilter filter) {
		return dao.getCount(filter);
	}

}

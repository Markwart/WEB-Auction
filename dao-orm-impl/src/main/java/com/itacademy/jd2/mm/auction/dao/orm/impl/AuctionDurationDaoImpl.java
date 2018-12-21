package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.IAuctionDurationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;

@Repository
public class AuctionDurationDaoImpl extends AbstractDaoImpl<IAuctionDuration, Integer> implements IAuctionDurationDao {

	protected AuctionDurationDaoImpl() {
		super(AuctionDuration.class);
	}

	@Override
	public IAuctionDuration createEntity() {
		final AuctionDuration auctionDuration = new AuctionDuration();
		return auctionDuration;
	}

	@Override
	public List<IAuctionDuration> find(AuctionDurationFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(AuctionDurationFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}

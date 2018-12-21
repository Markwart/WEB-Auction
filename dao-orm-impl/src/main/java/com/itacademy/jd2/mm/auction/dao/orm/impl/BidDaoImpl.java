package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Bid;
import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;

@Repository
public class BidDaoImpl extends AbstractDaoImpl<IBid, Integer> implements IBidDao {

	protected BidDaoImpl() {
		super(Bid.class);
	}

	@Override
	public IBid createEntity() {
		final Bid bid = new Bid();
		return bid;
	}

	@Override
	public List<IBid> find(BidFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(BidFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

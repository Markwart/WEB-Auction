package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.DeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;

@Repository
public class DeferredBidDaoImpl extends AbstractDaoImpl<IDeferredBid, Integer> implements IDeferredBidDao {

	protected DeferredBidDaoImpl() {
		super(DeferredBid.class);
	}

	@Override
	public IDeferredBid createEntity() {
		final DeferredBid deferredBid = new DeferredBid();
		return deferredBid;
	}

	@Override
	public List<IDeferredBid> find(DeferredBidFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(DeferredBidFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

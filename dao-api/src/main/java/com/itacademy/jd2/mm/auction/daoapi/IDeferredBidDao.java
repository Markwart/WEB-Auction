package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;

public interface IDeferredBidDao extends IDao<IDeferredBid, Integer> {

	List<IDeferredBid> find(DeferredBidFilter filter);

	long getCount(DeferredBidFilter filter);
}

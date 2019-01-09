package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;

public interface IBidDao extends IDao<IBid, Integer> {

	List<IBid> find(BidFilter filter);

	long getCount(BidFilter filter);

	IBid getFullInfo(Integer id);

	List<IBid> findRelatedBidsByItem(Integer id);

	List<IBid> findRelatedBidsByUser(Integer id);
}

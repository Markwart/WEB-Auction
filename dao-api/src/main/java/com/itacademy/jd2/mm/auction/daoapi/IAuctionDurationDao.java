package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;

public interface IAuctionDurationDao extends IDao<IAuctionDuration, Integer>{
	
	List<IAuctionDuration> find (AuctionDurationFilter filter);
	
	long getCount(AuctionDurationFilter filter);
}

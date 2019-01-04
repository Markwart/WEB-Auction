package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

public interface IItemDao extends IDao<IItem, Integer> {

	List<IItem> find(ItemFilter filter);

	long getCount(ItemFilter filter);

	IItem getFullInfo(Integer id);

	List<IItem> search(String text);

	List<IBid> findRelatedBids(Integer id);

	List<IDeferredBid> findRelatedDeferredBids(Integer id);

	List<IFeedback> findRelatedFeedback(Integer id);

	List<IMessage> findRelatedMessages(Integer id);

}

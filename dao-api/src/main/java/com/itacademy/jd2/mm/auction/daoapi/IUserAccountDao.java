package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	IUserAccount getPersonalData(final Integer id);
	
	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	IUserAccount getUserByLogin(String username);

	List<IBid> findRelatedBids(Integer id);

	List<IDeferredBid> findRelatedDeferredBids(Integer id);

	List<IItem> findRelatedItems(Integer id);

	List<IFeedback> findRelatedFeedback(Integer id);

	List<IMessage> findRelatedMessages(Integer id);

	List<IAdminCommunication> findRelatedAdminCommunication(Integer id);
}

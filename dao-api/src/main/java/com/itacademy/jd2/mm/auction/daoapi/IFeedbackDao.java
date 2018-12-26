package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;

public interface IFeedbackDao extends IDao<IFeedback, Integer> {

	List<IFeedback> find(FeedbackFilter filter);

	long getCount(FeedbackFilter filter);

	IFeedback getFullInfo(Integer id);
}

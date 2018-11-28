package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;

public interface IFeedbackService {

	IFeedback get(Integer id);

	List<IFeedback> getAll();

	void save(IFeedback entity);

	void delete(Integer id);

	void deleteAll();

	IFeedback createEntity();

	List<IFeedback> find(FeedbackFilter filter);

	long getCount(FeedbackFilter filter);
}

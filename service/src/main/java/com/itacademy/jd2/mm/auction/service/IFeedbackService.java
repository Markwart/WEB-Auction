package com.itacademy.jd2.mm.auction.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;

public interface IFeedbackService {

	IFeedback get(Integer id);

	List<IFeedback> getAll();

	@Transactional
	void save(IFeedback entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IFeedback createEntity();

	List<IFeedback> find(FeedbackFilter filter);

	long getCount(FeedbackFilter filter);

	IFeedback getFullInfo(Integer id);
}

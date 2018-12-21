package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Feedback;
import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;

@Repository
public class FeedbackDaoImpl extends AbstractDaoImpl<IFeedback, Integer> implements IFeedbackDao {

	protected FeedbackDaoImpl() {
		super(Feedback.class);
	}

	@Override
	public IFeedback createEntity() {
		final Feedback feedback = new Feedback();
		return feedback;
	}

	@Override
	public List<IFeedback> find(FeedbackFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(FeedbackFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

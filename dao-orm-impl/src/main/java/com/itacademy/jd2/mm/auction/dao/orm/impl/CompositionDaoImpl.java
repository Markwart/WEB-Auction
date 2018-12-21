package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Composition;
import com.itacademy.jd2.mm.auction.daoapi.ICompositionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;

@Repository
public class CompositionDaoImpl extends AbstractDaoImpl<IComposition, Integer> implements ICompositionDao {

	protected CompositionDaoImpl() {
		super(Composition.class);
	}

	@Override
	public IComposition createEntity() {
		final Composition composition = new Composition();
		return composition;
	}

	@Override
	public List<IComposition> find(CompositionFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CompositionFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.ShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.IShippingMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;

@Repository
public class ShippingMethodDaoImpl extends AbstractDaoImpl<IShippingMethod, Integer> implements IShippingMethodDao {

	protected ShippingMethodDaoImpl() {
		super(ShippingMethod.class);
	}

	@Override
	public IShippingMethod createEntity() {
		final ShippingMethod shippingMethod = new ShippingMethod();
		return shippingMethod;
	}

	@Override
	public List<IShippingMethod> find(ShippingMethodFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ShippingMethodFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

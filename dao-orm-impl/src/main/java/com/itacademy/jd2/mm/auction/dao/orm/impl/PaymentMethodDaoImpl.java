package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.PaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.IPaymentMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.PaymentMethodFilter;

@Repository
public class PaymentMethodDaoImpl extends AbstractDaoImpl<IPaymentMethod, Integer> implements IPaymentMethodDao {

	protected PaymentMethodDaoImpl() {
		super(PaymentMethod.class);
	}

	@Override
	public IPaymentMethod createEntity() {
		final PaymentMethod paymentMethod = new PaymentMethod();
		return paymentMethod;
	}

	@Override
	public List<IPaymentMethod> find(PaymentMethodFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(PaymentMethodFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}

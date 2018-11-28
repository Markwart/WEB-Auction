package com.itacademy.jd2.mm.auction.daoapi;

import java.util.List;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.PaymentMethodFilter;

public interface IPaymentMethodDao extends IDao<IPaymentMethod, Integer> {

	List<IPaymentMethod> find(PaymentMethodFilter filter);

	long getCount(PaymentMethodFilter filter);
}

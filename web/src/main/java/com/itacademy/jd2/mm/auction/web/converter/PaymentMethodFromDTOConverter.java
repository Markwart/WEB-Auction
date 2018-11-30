package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.service.IPaymentMethodService;
import com.itacademy.jd2.mm.auction.web.dto.PaymentMethodDTO;

@Component
public class PaymentMethodFromDTOConverter implements Function<PaymentMethodDTO, IPaymentMethod> {

	@Autowired
	private IPaymentMethodService paymentMethodService;

	@Override
	public IPaymentMethod apply(final PaymentMethodDTO dto) {
		final IPaymentMethod entity = paymentMethodService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}

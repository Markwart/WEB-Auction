package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.web.dto.PaymentMethodDTO;

@Component
public class PaymentMethodToDTOConverter implements Function<IPaymentMethod, PaymentMethodDTO> {

	@Override
	public PaymentMethodDTO apply(final IPaymentMethod entity) {
		final PaymentMethodDTO dto = new PaymentMethodDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

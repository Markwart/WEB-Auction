package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.web.dto.ShippingMethodDTO;

@Component
public class ShippingMethodToDTOConverter implements Function<IShippingMethod, ShippingMethodDTO> {

	@Override
	public ShippingMethodDTO apply(final IShippingMethod entity) {
		final ShippingMethodDTO dto = new ShippingMethodDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDeliveryTime(entity.getDeliveryTime());
		dto.setCost(entity.getCost());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.service.IShippingMethodService;
import com.itacademy.jd2.mm.auction.web.dto.ShippingMethodDTO;

@Component
public class ShippingMethodFromDTOConverter implements Function<ShippingMethodDTO, IShippingMethod> {

	@Autowired
	private IShippingMethodService shippingMethodService;

	@Override
	public IShippingMethod apply(final ShippingMethodDTO dto) {
		final IShippingMethod entity = shippingMethodService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDeliveryTime(dto.getDeliveryTime());
		entity.setCost(dto.getCost());
		return entity;
	}
}

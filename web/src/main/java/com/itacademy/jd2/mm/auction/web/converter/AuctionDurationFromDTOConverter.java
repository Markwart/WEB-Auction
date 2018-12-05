package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.service.IAuctionDurationService;
import com.itacademy.jd2.mm.auction.web.dto.AuctionDurationDTO;

@Component
public class AuctionDurationFromDTOConverter implements Function<AuctionDurationDTO, IAuctionDuration> {

	@Autowired
	private IAuctionDurationService auctionDurationService;

	@Override
	public IAuctionDuration apply(final AuctionDurationDTO dto) {
		final IAuctionDuration entity = auctionDurationService.createEntity();
		entity.setId(dto.getId());
		entity.setMin(dto.getMin());
		return entity;
	}
}

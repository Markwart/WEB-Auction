package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.web.dto.AuctionDurationDTO;

@Component
public class AuctionDurationToDTOConverter implements Function<IAuctionDuration, AuctionDurationDTO> {

	@Override
	public AuctionDurationDTO apply(final IAuctionDuration entity) {
		final AuctionDurationDTO dto = new AuctionDurationDTO();
		dto.setId(entity.getId());
		dto.setDay(entity.getDay());;
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

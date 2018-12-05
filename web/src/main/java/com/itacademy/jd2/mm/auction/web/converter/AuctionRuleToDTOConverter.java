package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.web.dto.AuctionRuleDTO;

@Component
public class AuctionRuleToDTOConverter implements Function<IAuctionRule, AuctionRuleDTO> {

	@Override
	public AuctionRuleDTO apply(final IAuctionRule entity) {
		final AuctionRuleDTO dto = new AuctionRuleDTO();
		dto.setId(entity.getId());
		dto.setIndex(entity.getIndex());
		dto.setTheme(entity.getTheme());
		dto.setText(entity.getText());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

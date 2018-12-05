package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.service.IAuctionRuleService;
import com.itacademy.jd2.mm.auction.web.dto.AuctionRuleDTO;

@Component
public class AuctionRuleFromDTOConverter implements Function<AuctionRuleDTO, IAuctionRule> {

	@Autowired
	private IAuctionRuleService auctionRuleService;

	@Override
	public IAuctionRule apply(final AuctionRuleDTO dto) {
		final IAuctionRule entity = auctionRuleService.createEntity();
		entity.setId(dto.getId());
		entity.setIndex(dto.getIndex());
		entity.setTheme(dto.getTheme());
		entity.setText(dto.getText());
		return entity;
	}
}

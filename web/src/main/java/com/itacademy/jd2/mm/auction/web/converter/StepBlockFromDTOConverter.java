package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.service.IStepBlockService;
import com.itacademy.jd2.mm.auction.web.dto.StepBlockDTO;

@Component
public class StepBlockFromDTOConverter implements Function<StepBlockDTO, IStepBlock> {

	@Autowired
	private IStepBlockService stepBlockService;

	@Override
	public IStepBlock apply(final StepBlockDTO dto) {
		final IStepBlock entity = stepBlockService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setStep_1(dto.getStep_1());
		entity.setStep_2(dto.getStep_2());
		entity.setStep_3(dto.getStep_3());
		entity.setStep_4(dto.getStep_4());
		entity.setStep_5(dto.getStep_5());
		return entity;
	}
}

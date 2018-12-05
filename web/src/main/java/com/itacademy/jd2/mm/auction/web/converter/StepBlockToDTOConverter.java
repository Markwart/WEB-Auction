package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.web.dto.StepBlockDTO;

@Component
public class StepBlockToDTOConverter implements Function<IStepBlock, StepBlockDTO> {

	@Override
	public StepBlockDTO apply(final IStepBlock entity) {
		final StepBlockDTO dto = new StepBlockDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStep_1(entity.getStep_1());
		dto.setStep_2(entity.getStep_2());
		dto.setStep_3(entity.getStep_3());
		dto.setStep_4(entity.getStep_4());
		dto.setStep_5(entity.getStep_5());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

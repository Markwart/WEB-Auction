package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.web.dto.ConditionDTO;


@Component
public class ConditionToDTOConverter implements Function<ICondition, ConditionDTO> {

    @Override
    public ConditionDTO apply(final ICondition entity) {
        final ConditionDTO dto = new ConditionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

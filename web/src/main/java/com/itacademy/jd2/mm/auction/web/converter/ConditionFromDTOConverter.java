package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.web.dto.ConditionDTO;

@Component
public class ConditionFromDTOConverter implements Function<ConditionDTO, ICondition> {

    @Autowired
    private IConditionService conditionService;

    @Override
    public ICondition apply(final ConditionDTO dto) {
        final ICondition entity = conditionService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}

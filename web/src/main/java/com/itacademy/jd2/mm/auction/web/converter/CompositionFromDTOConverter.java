package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.web.dto.CompositionDTO;

@Component
public class CompositionFromDTOConverter implements Function<CompositionDTO, IComposition> {

    @Autowired
    private ICompositionService compositionService;

    @Override
    public IComposition apply(final CompositionDTO dto) {
        final IComposition entity = compositionService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}

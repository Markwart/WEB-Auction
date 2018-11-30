package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.web.dto.CompositionDTO;


@Component
public class CompositionToDTOConverter implements Function<IComposition, CompositionDTO> {

    @Override
    public CompositionDTO apply(final IComposition entity) {
        final CompositionDTO dto = new CompositionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

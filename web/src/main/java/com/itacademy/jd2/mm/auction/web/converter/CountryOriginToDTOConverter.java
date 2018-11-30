package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.web.dto.CountryOriginDTO;


@Component
public class CountryOriginToDTOConverter implements Function<ICountryOrigin, CountryOriginDTO> {

    @Override
    public CountryOriginDTO apply(final ICountryOrigin entity) {
        final CountryOriginDTO dto = new CountryOriginDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

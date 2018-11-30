package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.web.dto.CountryOriginDTO;

@Component
public class CountryOriginFromDTOConverter implements Function<CountryOriginDTO, ICountryOrigin> {

    @Autowired
    private ICountryOriginService countryOriginService;

    @Override
    public ICountryOrigin apply(final CountryOriginDTO dto) {
        final ICountryOrigin entity = countryOriginService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}

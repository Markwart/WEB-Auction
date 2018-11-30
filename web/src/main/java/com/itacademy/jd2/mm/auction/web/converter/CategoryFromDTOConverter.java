package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.service.ICategoryService;
import com.itacademy.jd2.mm.auction.web.dto.CategoryDTO;

@Component
public class CategoryFromDTOConverter implements Function<CategoryDTO, ICategory> {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public ICategory apply(final CategoryDTO dto) {
        final ICategory entity = categoryService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}

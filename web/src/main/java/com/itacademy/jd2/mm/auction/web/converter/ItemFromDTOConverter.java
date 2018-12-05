package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.ICategoryService;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.ItemDTO;

@Component
public class ItemFromDTOConverter implements Function<ItemDTO, IItem> {

	@Autowired
	private IItemService itemService;
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICountryOriginService countryOriginService;
    @Autowired
    private IConditionService conditionService;
    @Autowired
    private ICompositionService compositionService;
    
    @Override
    public IItem apply(final ItemDTO dto) {
        final IItem entity = itemService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setText(dto.getText());
        entity.setStartingPrice(dto.getStartingPrice());
        entity.setStatusAuction(dto.getStatusAuction());
        entity.setAuctionEnd(dto.getAuctionEnd());
        entity.setYear(dto.getYear());

        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getSellerId());
        entity.setSeller(userAccount);
        
        final ICategory category = categoryService.createEntity();
        category.setId(dto.getCategoryId());
        entity.setCategory(category);
        
        final ICondition condition = conditionService.createEntity();
        condition.setId(dto.getConditionId());
        entity.setCondition(condition);
        
        final IComposition composition = compositionService.createEntity();
        composition.setId(dto.getCompositionId());
        entity.setComposition(composition);
        
        final ICountryOrigin countryOrigin = countryOriginService.createEntity();
        countryOrigin.setId(dto.getCountryOriginId());
        entity.setCountryOrigin(countryOrigin);
        
        return entity;
    }
}

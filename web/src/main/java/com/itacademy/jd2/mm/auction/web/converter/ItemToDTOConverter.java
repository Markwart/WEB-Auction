package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.ItemDTO;

@Component
public class ItemToDTOConverter implements Function<IItem, ItemDTO> {

	@Override
	public ItemDTO apply(final IItem entity) {
		final ItemDTO dto = new ItemDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setYear(entity.getYear());
		dto.setText(entity.getText());
		dto.setAuctionEnd(entity.getAuctionEnd());
		dto.setStartingPrice(entity.getStartingPrice());
		dto.setStatusAuction(entity.getStatusAuction().name());

		final IUserAccount userAccount = entity.getSeller();
		if (userAccount != null) {
			dto.setSellerId(userAccount.getId());
			dto.setSellerEmail(userAccount.getEmail());
		}

		final ICategory category = entity.getCategory();
		if (category != null) {
			dto.setCategoryId(category.getId());
			dto.setCategoryName(category.getName());
		}

		final ICondition condition = entity.getCondition();
		if (condition != null) {
			dto.setConditionId(condition.getId());
			dto.setConditionName(condition.getName());
		}

		final IComposition composition = entity.getComposition();
		if (composition != null) {
			dto.setCompositionId(composition.getId());
			dto.setCompositionName(composition.getName());
		}

		final ICountryOrigin countryOrigin = entity.getCountryOrigin();
		if (countryOrigin != null) {
			dto.setCountryOriginId(countryOrigin.getId());
			dto.setCountryOriginName(countryOrigin.getName());
		}

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

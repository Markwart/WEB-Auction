package com.itacademy.jd2.mm.auction.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.ItemDTO;

@Component
public class ItemToDTOConverter implements Function<IItem, ItemDTO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemToDTOConverter.class);

	@Override
	public ItemDTO apply(final IItem entity) {
		final ItemDTO dto = new ItemDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setYear(entity.getYear());
		dto.setText(entity.getText());
		dto.setStartingPrice(entity.getStartingPrice());
		dto.setStatusAuction(entity.getStatusAuction());

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

		final IAuctionDuration duration = entity.getDuration();
		if (duration != null) {
			dto.setDurationId(duration.getId());
			dto.setDurationDay(duration.getDay());
		}

		try {
			final Set<IShippingMethod> shippingMethods = entity.getShippingMethods();
			if (shippingMethods != null) {
				dto.setShippingMethodsIds(
						shippingMethods.stream().map(IShippingMethod::getId).collect(Collectors.toSet()));
				dto.setShippingMethodsNames(
						shippingMethods.stream().map(IShippingMethod::getName).collect(Collectors.toSet()));
			}
		} catch (final Exception e) {
			LOGGER.warn("ignore conversion of 'shipping-methods' collection because of:" + e.getMessage());
		}

		try {
			final Set<IPaymentMethod> paymentMethods = entity.getPaymentMethods();
			if (paymentMethods != null) {
				dto.setPaymentMethodsIds(
						paymentMethods.stream().map(IPaymentMethod::getId).collect(Collectors.toSet()));
				dto.setPaymentMethodsNames(
						paymentMethods.stream().map(IPaymentMethod::getName).collect(Collectors.toSet()));
			}
		} catch (final Exception e) {
			LOGGER.warn("ignore conversion of 'payment-methods' collection because of:" + e.getMessage());
		}

		dto.setAuctionEnd(entity.getAuctionEnd());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}

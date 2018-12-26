package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.DeferredBidDTO;

@Component
public class DeferredBidToDTOConverter implements Function<IDeferredBid, DeferredBidDTO> {

	@Override
	public DeferredBidDTO apply(final IDeferredBid entity) {
		final DeferredBidDTO dto = new DeferredBidDTO();
		dto.setId(entity.getId());
		dto.setPriceBid(entity.getPriceBid());
		dto.setStatusBid(entity.getStatusBid().name());

		final IUserAccount userAccount = entity.getUserBid();
		if (userAccount != null) {
			dto.setUserBidId(userAccount.getId());
			dto.setUserBidEmail(userAccount.getEmail());
		}

		final IItem item = entity.getItem();
		if (item != null) {
			dto.setItemId(item.getId());
			dto.setItemName(item.getName());
		}

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}

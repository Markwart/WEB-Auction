package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.BidDTO;

@Component
public class BidToDTOConverter implements Function<IBid, BidDTO> {

	@Override
	public BidDTO apply(final IBid entity) {
		final BidDTO dto = new BidDTO();
		dto.setId(entity.getId());
		dto.setPriceBid(entity.getPriceBid());
		dto.setStatusBid(entity.getStatusBid());

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

package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.FeedbackDTO;


@Component
public class FeedbackToDTOConverter implements Function<IFeedback, FeedbackDTO> {

    @Override
    public FeedbackDTO apply(final IFeedback entity) {
        final FeedbackDTO dto = new FeedbackDTO();
        dto.setId(entity.getId());
        dto.setCommunication(entity.getCommunication());
        dto.setShippingTime(entity.getShippingTime());
        dto.setShippingCharges(entity.getShippingCharges());
        dto.setItemDescription(entity.getItemDescription());
        dto.setComment(entity.getComment());
        
        final IUserAccount userAccountFrom = entity.getUserFrom();
        if (userAccountFrom != null) {
            dto.setUserFromId(userAccountFrom.getId());
            dto.setUserFromEmail(userAccountFrom.getEmail());
        }
        
        final IUserAccount userAccountWhom = entity.getUserWhom();
        if (userAccountWhom != null) {
            dto.setUserWhomId(userAccountWhom.getId());
            dto.setUserWhomEmail(userAccountWhom.getEmail());
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

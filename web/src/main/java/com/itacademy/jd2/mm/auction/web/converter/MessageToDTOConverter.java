package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.MessageDTO;

@Component
public class MessageToDTOConverter implements Function<IMessage, MessageDTO> {

    @Override
    public MessageDTO apply(final IMessage entity) {
        final MessageDTO dto = new MessageDTO();
        dto.setId(entity.getId());
        dto.setTheme(entity.getTheme());
        dto.setText(entity.getText());
        
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

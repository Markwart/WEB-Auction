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
        
        final IUserAccount userAccountFrom = entity.getUserAccountFrom();
        if (userAccountFrom != null) {
            dto.setUserAccountFromId(userAccountFrom.getId());
            dto.setUserAccountFromEmail(userAccountFrom.getEmail());
        }
        
        final IUserAccount userAccountWhom = entity.getUserAccountWhom();
        if (userAccountWhom != null) {
            dto.setUserAccountWhomId(userAccountWhom.getId());
            dto.setUserAccountWhomEmail(userAccountWhom.getEmail());
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

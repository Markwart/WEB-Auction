package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.AdminCommunicationDTO;


@Component
public class AdminCommunicationToDTOConverter implements Function<IAdminCommunication, AdminCommunicationDTO> {

    @Override
    public AdminCommunicationDTO apply(final IAdminCommunication entity) {
        final AdminCommunicationDTO dto = new AdminCommunicationDTO();
        dto.setId(entity.getId());
        dto.setTheme(entity.getTheme());
        dto.setText(entity.getText());
        
        final IUserAccount userAccount = entity.getUserFrom();
        if (userAccount != null) {
            dto.setUserFromId(userAccount.getId());
            dto.setUserFromEmail(userAccount.getEmail());
        }
        
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

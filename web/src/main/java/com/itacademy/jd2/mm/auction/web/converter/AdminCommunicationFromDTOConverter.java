package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IAdminCommunicationService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.AdminCommunicationDTO;

@Component
public class AdminCommunicationFromDTOConverter implements Function<AdminCommunicationDTO, IAdminCommunication> {

    @Autowired
    private IAdminCommunicationService adminCommunicationService;
    @Autowired
    private IUserAccountService userAccountService;

    @Override
    public IAdminCommunication apply(final AdminCommunicationDTO dto) {
        final IAdminCommunication entity = adminCommunicationService.createEntity();
        entity.setId(dto.getId());
        entity.setTheme(dto.getTheme());
        entity.setText(dto.getText());

        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getUserFromId());
        entity.setUserFrom(userAccount);
        
        return entity;
    }
}

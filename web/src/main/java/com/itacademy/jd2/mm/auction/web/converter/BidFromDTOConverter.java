package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IBidService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.BidDTO;

@Component
public class BidFromDTOConverter implements Function<BidDTO, IBid> {

    @Autowired
    private IBidService bidService;
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private IItemService itemService;

    @Override
    public IBid apply(final BidDTO dto) {
        final IBid entity = bidService.createEntity();
        entity.setId(dto.getId());
        entity.setPriceBid(dto.getPriceBid());
        entity.setStatusBid(dto.getStatusBid());

        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getUserAccountId());
        entity.setUserAccount(userAccount);
        
        final IItem item = itemService.createEntity();
        item.setId(dto.getItemId());
        entity.setItem(item);
        
        return entity;
    }
}

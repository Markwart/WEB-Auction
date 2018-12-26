package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IDeferredBidService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.DeferredBidDTO;

@Component
public class DeferredBidFromDTOConverter implements Function<DeferredBidDTO, IDeferredBid> {

    @Autowired
    private IDeferredBidService bidService;
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private IItemService itemService;

    @Override
    public IDeferredBid apply(final DeferredBidDTO dto) {
        final IDeferredBid entity = bidService.createEntity();
        entity.setId(dto.getId());
        entity.setPriceBid(dto.getPriceBid());
        entity.setStatusBid(StatusBid.valueOf(dto.getStatusBid()));

        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getUserBidId());
        entity.setUserBid(userAccount);
        
        final IItem item = itemService.createEntity();
        item.setId(dto.getItemId());
        entity.setItem(item);
        
        return entity;
    }
}

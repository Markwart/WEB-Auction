package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IFeedbackService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.FeedbackDTO;

@Component
public class FeedbackFromDTOConverter implements Function<FeedbackDTO, IFeedback> {

	@Autowired
	private IFeedbackService feedbackService;
	@Autowired
	private IUserAccountService userAccountFromService;
	@Autowired
	private IUserAccountService userAccountWhomService;
	@Autowired
	private IItemService itemService;

	@Override
	public IFeedback apply(final FeedbackDTO dto) {
		final IFeedback entity = feedbackService.createEntity();
		entity.setId(dto.getId());
		entity.setCommunication(dto.getCommunication());
		entity.setShippingTime(dto.getShippingTime());
		entity.setShippingCharges(dto.getShippingCharges());
		entity.setItemDescription(dto.getItemDescription());
		entity.setComment(dto.getComment());

		final IUserAccount userAccountFrom = userAccountFromService.createEntity();
		userAccountFrom.setId(dto.getUserAccountFromId());
		entity.setUserAccountFrom(userAccountFrom);

		final IUserAccount userAccountWhom = userAccountWhomService.createEntity();
		userAccountWhom.setId(dto.getUserAccountWhomId());
		entity.setUserAccountWhom(userAccountWhom);
		
		final IItem item = itemService.createEntity();
		item.setId(dto.getItemId());
		entity.setItem(item);

		return entity;
	}
}

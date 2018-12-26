package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IMessageService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.MessageDTO;

@Component
public class MessageFromDTOConverter implements Function<MessageDTO, IMessage> {

	@Autowired
	private IMessageService messageService;
	@Autowired
	private IUserAccountService userAccountFromService;
	@Autowired
	private IUserAccountService userAccountWhomService;
	@Autowired
	private IItemService itemService;

	@Override
	public IMessage apply(final MessageDTO dto) {
		final IMessage entity = messageService.createEntity();
		entity.setId(dto.getId());
		entity.setTheme(dto.getTheme());
		entity.setText(dto.getText());

		final IUserAccount userAccountFrom = userAccountFromService.createEntity();
		userAccountFrom.setId(dto.getUserFromId());
		entity.setUserFrom(userAccountFrom);

		final IUserAccount userAccountWhom = userAccountWhomService.createEntity();
		userAccountWhom.setId(dto.getUserWhomId());
		entity.setUserWhom(userAccountWhom);
		
		final IItem item = itemService.createEntity();
		item.setId(dto.getItemId());
		entity.setItem(item);

		return entity;
	}
}

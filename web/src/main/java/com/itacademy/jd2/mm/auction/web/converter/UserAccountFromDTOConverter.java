package com.itacademy.jd2.mm.auction.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IItemService itemService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setRole(dto.getRole());
		entity.setPassword(dto.getPassword());

		final IPersonalData personalDataEntity = userAccountService.createPersonalDataEntity();
		personalDataEntity.setId(dto.getId());
		personalDataEntity.setFirstName(dto.getPersonalData().getFirstName());
		personalDataEntity.setLastName(dto.getPersonalData().getLastName());
		personalDataEntity.setAdress(dto.getPersonalData().getAdress());
		personalDataEntity.setCountry(dto.getPersonalData().getCountry());
		personalDataEntity.setCity(dto.getPersonalData().getCity());
		personalDataEntity.setUsername(dto.getPersonalData().getUsername());
		entity.setPersonalData(personalDataEntity);
		
		 final Set<Integer> itemsIds = dto.getItemsIds();
	        if (CollectionUtils.isNotEmpty(itemsIds)) {
	            entity.setItems(itemsIds.stream().map((id) -> {
	                final IItem item = itemService.createEntity();
	                item.setId(id);
	                return item;
	            }).collect(Collectors.toSet()));
	        }

		return entity;
	}
}

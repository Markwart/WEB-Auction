package com.itacademy.jd2.mm.auction.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountToDTOConverter.class);

	@Override
	public UserAccountDTO apply(final IUserAccount entity) {
		final UserAccountDTO dto = new UserAccountDTO();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setRole(entity.getRole());
		dto.setPassword(entity.getPassword());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		final IPersonalData personalDataEntity = entity.getPersonalData();
		if (personalDataEntity != null) {
			dto.getPersonalData().setFirstName(personalDataEntity.getFirstName());
			dto.getPersonalData().setLastName(personalDataEntity.getLastName());
			dto.getPersonalData().setAdress(personalDataEntity.getAdress());
			dto.getPersonalData().setCountry(personalDataEntity.getCountry());
			dto.getPersonalData().setCity(personalDataEntity.getCity());
			dto.getPersonalData().setUsername(personalDataEntity.getUsername());
		}

		try {
			final Set<IItem> items = entity.getItems();
			if (items != null) {
				dto.setItemsIds(items.stream().map(IItem::getId).collect(Collectors.toSet()));
			}
		} catch (final Exception e) {
			LOGGER.warn("ignore conversion of 'items' collection because of:" + e.getMessage());
		}

		return dto;
	}

}

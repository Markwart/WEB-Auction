package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {

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
			dto.getPersonalData().setUserName(personalDataEntity.getUserName());
		}

		return dto;
	}

}

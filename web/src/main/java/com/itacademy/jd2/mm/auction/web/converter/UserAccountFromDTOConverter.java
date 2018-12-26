package com.itacademy.jd2.mm.auction.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

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
		personalDataEntity.setUsername(dto.getPersonalData().getUsername());
		entity.setPersonalData(personalDataEntity);

		return entity;
	}
}

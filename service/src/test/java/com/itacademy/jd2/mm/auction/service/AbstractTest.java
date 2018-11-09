package com.itacademy.jd2.mm.auction.service;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.impl.AdminCommunicationServiceImpl;
import com.itacademy.jd2.mm.auction.service.impl.CompositionServiceImpl;
import com.itacademy.jd2.mm.auction.service.impl.ConditionServiceImpl;
import com.itacademy.jd2.mm.auction.service.impl.UserAccountServiceImpl;

public abstract class AbstractTest {

	protected IConditionService conditionService = new ConditionServiceImpl();
	protected ICompositionService compositionService = new CompositionServiceImpl();
	protected IUserAccountService userAccountService = new UserAccountServiceImpl();
	protected IAdminCommunicationService adminCommunicationService = new AdminCommunicationServiceImpl();

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		adminCommunicationService.deleteAll();
		conditionService.deleteAll();
		compositionService.deleteAll();
		userAccountService.deleteAll();

	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected ICondition saveNewCondition() {
		final ICondition entity = conditionService.createEntity();
		entity.setName("condition-" + getRandomPrefix());
		conditionService.save(entity);
		return entity;
	}

	protected IComposition saveNewComposition() {
		final IComposition entity = compositionService.createEntity();
		entity.setName("composition-" + getRandomPrefix());
		compositionService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setRole(getRANDOM().nextInt(2));
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		userAccountService.save(entity);
		return entity;
	}
}

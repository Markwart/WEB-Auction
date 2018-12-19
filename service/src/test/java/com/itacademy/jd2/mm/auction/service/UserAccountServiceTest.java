package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IUserAccount entity = saveNewUserAccount();

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getRole(), entityFromDb.getRole());
		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IUserAccount entity = saveNewUserAccount();

		String newEmail = entity.getEmail() + "_updated";
		entity.setEmail(newEmail);
		Thread.sleep(DELAY);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newEmail, entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getRole(), entityFromDb.getRole());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int initialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getEmail());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();
		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUserAccount();
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}

	@Test
	public void createWithPersonalDataTest() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setRole(getRANDOM().nextInt(2));
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());

		final IPersonalData personalDataEntity = userAccountService.createPersonalDataEntity();
		personalDataEntity.setUserName("username-" + getRandomPrefix());
		personalDataEntity.setFirstName("first_name-" + getRandomPrefix());
		personalDataEntity.setLastName("last_name-" + getRandomPrefix());
		personalDataEntity.setAdress("adress-" + getRandomPrefix());

		userAccountService.save(entity, personalDataEntity);

		final IUserAccount entityFromDb = userAccountService.getPersonalData(entity.getId());

		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getRole(), entityFromDb.getRole());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		final IPersonalData infoEntityFromDb = entityFromDb.getPersonalData();
		assertNotNull(infoEntityFromDb.getId());
		assertEquals(personalDataEntity.getFirstName(), infoEntityFromDb.getFirstName());
		assertEquals(personalDataEntity.getLastName(), infoEntityFromDb.getLastName());
		assertEquals(personalDataEntity.getAdress(), infoEntityFromDb.getAdress());
		assertEquals(personalDataEntity.getUserName(), infoEntityFromDb.getUserName());
		assertNotNull(infoEntityFromDb.getCreated());
		assertNotNull(infoEntityFromDb.getUpdated());
		assertTrue(infoEntityFromDb.getCreated().equals(infoEntityFromDb.getUpdated()));
	}
}

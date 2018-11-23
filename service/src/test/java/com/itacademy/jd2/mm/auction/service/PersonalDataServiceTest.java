package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;

public class PersonalDataServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IPersonalData entity = saveNewPersonalData();

		final IPersonalData entityFromDb = personalDataService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getUsername(), entityFromDb.getUsername());
		assertEquals(entity.getFirstName(), entityFromDb.getFirstName());
		assertEquals(entity.getLastName(), entityFromDb.getLastName());
		assertEquals(entity.getAdress(), entityFromDb.getAdress());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IPersonalData entity = saveNewPersonalData();

		String newUserName = entity.getUsername() + "_updated";
		entity.setUserName(newUserName);
		Thread.sleep(DELAY);
		personalDataService.save(entity);

		final IPersonalData entityFromDb = personalDataService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getUsername(), entityFromDb.getUsername());
		assertEquals(entity.getFirstName(), entityFromDb.getFirstName());
		assertEquals(entity.getLastName(), entityFromDb.getLastName());
		assertEquals(entity.getAdress(), entityFromDb.getAdress());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = personalDataService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPersonalData();
		}

		final List<IPersonalData> allEntities = personalDataService.getAll();

		for (final IPersonalData entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getUsername());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getLastName());
			assertNotNull(entityFromDb.getAdress());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPersonalData entity = saveNewPersonalData();
		personalDataService.delete(entity.getId());
		assertNull(personalDataService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPersonalData();
		personalDataService.deleteAll();
		assertEquals(0, personalDataService.getAll().size());
	}

}

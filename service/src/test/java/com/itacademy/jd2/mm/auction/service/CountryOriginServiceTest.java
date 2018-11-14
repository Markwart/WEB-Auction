package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;

public class CountryOriginServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final ICountryOrigin entity = saveNewCountryOrigin();

		final ICountryOrigin entityFromDb = countryOriginService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final ICountryOrigin entity = saveNewCountryOrigin();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		countryOriginService.save(entity);

		final ICountryOrigin entityFromDb = countryOriginService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = countryOriginService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountryOrigin();
		}

		final List<ICountryOrigin> allEntities = countryOriginService.getAll();

		for (final ICountryOrigin entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICountryOrigin entity = saveNewCountryOrigin();
		countryOriginService.delete(entity.getId());
		assertNull(countryOriginService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCountryOrigin();
		countryOriginService.deleteAll();
		assertEquals(0, countryOriginService.getAll().size());
	}

}

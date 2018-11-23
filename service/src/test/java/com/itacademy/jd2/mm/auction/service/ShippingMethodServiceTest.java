package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;

public class ShippingMethodServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IShippingMethod entity = saveNewShippingMethod();

		final IShippingMethod entityFromDb = shippingMethodService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getDeliveryTime(), entityFromDb.getDeliveryTime());
		assertEquals(entity.getCost(), entityFromDb.getCost());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IShippingMethod entity = saveNewShippingMethod();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(DELAY);
		shippingMethodService.save(entity);

		final IShippingMethod entityFromDb = shippingMethodService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getDeliveryTime(), entityFromDb.getDeliveryTime());
		assertEquals(entity.getCost(), entityFromDb.getCost());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = shippingMethodService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewShippingMethod();
		}

		final List<IShippingMethod> allEntities = shippingMethodService.getAll();

		for (final IShippingMethod entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getDeliveryTime());
			assertNotNull(entityFromDb.getCost());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IShippingMethod entity = saveNewShippingMethod();
		shippingMethodService.delete(entity.getId());
		assertNull(shippingMethodService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewShippingMethod();
		shippingMethodService.deleteAll();
		assertEquals(0, shippingMethodService.getAll().size());
	}

}

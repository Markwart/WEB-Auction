package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;

public class PaymentMethodServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IPaymentMethod entity = saveNewPaymentMethod();

		final IPaymentMethod entityFromDb = paymentMethodService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IPaymentMethod entity = saveNewPaymentMethod();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(DELAY);
		paymentMethodService.save(entity);

		final IPaymentMethod entityFromDb = paymentMethodService.get(entity.getId());

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
		final int initialCount = paymentMethodService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPaymentMethod();
		}

		final List<IPaymentMethod> allEntities = paymentMethodService.getAll();

		for (final IPaymentMethod entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPaymentMethod entity = saveNewPaymentMethod();
		paymentMethodService.delete(entity.getId());
		assertNull(paymentMethodService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPaymentMethod();
		paymentMethodService.deleteAll();
		assertEquals(0, paymentMethodService.getAll().size());
	}
}

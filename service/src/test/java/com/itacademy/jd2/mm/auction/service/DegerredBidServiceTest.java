package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;

public class DegerredBidServiceTest extends AbstractTest {
	@Test
	public void testCreate() {
		final IDeferredBid entity = saveNewDeferredBid();

		final IDeferredBid entityFromDb = deferredBidService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getPriceBid(), entityFromDb.getPriceBid());
		assertEquals(entity.getStatusBid(), entityFromDb.getStatusBid());
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IDeferredBid entity = saveNewDeferredBid();

		String newStatusBid = entity.getStatusBid() + "_updated";
		entity.setStatusBid(newStatusBid);
		Thread.sleep(2000);
		deferredBidService.save(entity);

		final IDeferredBid entityFromDb = deferredBidService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
		assertEquals(entity.getPriceBid(), entityFromDb.getPriceBid());
		assertEquals(entity.getStatusBid(), entityFromDb.getStatusBid());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		;
	}

	@Test
	public void testGetAll() {
		final int initialCount = deferredBidService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewDeferredBid();
		}

		final List<IDeferredBid> allEntities = deferredBidService.getAll();

		for (final IDeferredBid entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getItem().getId());
			assertNotNull(entityFromDb.getUserAccount().getId());
			assertNotNull(entityFromDb.getPriceBid());
			assertNotNull(entityFromDb.getStatusBid());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IDeferredBid entity = saveNewDeferredBid();
		deferredBidService.delete(entity.getId());
		assertNull(deferredBidService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewDeferredBid();
		deferredBidService.deleteAll();
		assertEquals(0, deferredBidService.getAll().size());
	}

}

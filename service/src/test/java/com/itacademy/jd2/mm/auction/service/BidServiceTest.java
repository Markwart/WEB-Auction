package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;

public class BidServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IBid entity = saveNewBid();

		final IBid entityFromDb = bidService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getPriceBid(), entityFromDb.getPriceBid());
		assertEquals(entity.getStatusBid(), entityFromDb.getStatusBid());
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserBid().getId(), entityFromDb.getUserBid().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IBid entity = saveNewBid();

		@SuppressWarnings("static-access")
		StatusBid newStatusBid = entity.getStatusBid().outbidded;
		entity.setStatusBid(newStatusBid);
		Thread.sleep(DELAY);
		bidService.save(entity, null, null);

		final IBid entityFromDb = bidService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserBid().getId(), entityFromDb.getUserBid().getId());
		assertEquals(entity.getPriceBid(), entityFromDb.getPriceBid());
		assertEquals(entity.getStatusBid(), entityFromDb.getStatusBid());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = bidService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBid();
		}

		final List<IBid> allEntities = bidService.getAll();

		for (final IBid entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getItem());
			assertNotNull(entityFromDb.getUserBid());
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
		final IBid entity = saveNewBid();
		bidService.delete(entity.getId());
		assertNull(bidService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBid();
		bidService.deleteAll();
		assertEquals(0, bidService.getAll().size());
	}
}

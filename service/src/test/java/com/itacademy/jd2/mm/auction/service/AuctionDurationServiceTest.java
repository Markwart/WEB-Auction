package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;

public class AuctionDurationServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IAuctionDuration entity = saveNewAuctionDuration();

		final IAuctionDuration entityFromDb = auctionDurationService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getMin(), entityFromDb.getMin());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IAuctionDuration entity = saveNewAuctionDuration();

		Integer newMin = entity.getMin() + getRandomObjectsCount();
		entity.setMin(newMin);
		Thread.sleep(DELAY);
		auctionDurationService.save(entity);

		final IAuctionDuration entityFromDb = auctionDurationService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getMin(), entityFromDb.getMin());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		;
	}

	@Test
	public void testGetAll() {
		final int initialCount = auctionDurationService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewAuctionDuration();
		}

		final List<IAuctionDuration> allEntities = auctionDurationService.getAll();

		for (final IAuctionDuration entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getMin());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IAuctionDuration entity = saveNewAuctionDuration();
		auctionDurationService.delete(entity.getId());
		assertNull(auctionDurationService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewAuctionDuration();
		auctionDurationService.deleteAll();
		assertEquals(0, auctionDurationService.getAll().size());
	}

}

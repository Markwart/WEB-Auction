package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;

public class AuctionRuleServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final IAuctionRule entity = saveNewAuctionRule();

		final IAuctionRule entityFromDb = auctionRuleService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getIndex(), entityFromDb.getIndex());
		assertEquals(entity.getTheme(), entityFromDb.getTheme());
		assertEquals(entity.getText(), entityFromDb.getText());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IAuctionRule entity = saveNewAuctionRule();

		String newIndex = entity.getIndex() + "_updated";
		entity.setIndex(newIndex);
		Thread.sleep(DELAY);
		auctionRuleService.save(entity);

		final IAuctionRule entityFromDb = auctionRuleService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getIndex(), entityFromDb.getIndex());
		assertEquals(entity.getTheme(), entityFromDb.getTheme());
		assertEquals(entity.getText(), entityFromDb.getText());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = auctionRuleService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewAuctionRule();
		}

		final List<IAuctionRule> allEntities = auctionRuleService.getAll();

		for (final IAuctionRule entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getIndex());
			assertNotNull(entityFromDb.getTheme());
			assertNotNull(entityFromDb.getText());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IAuctionRule entity = saveNewAuctionRule();
		auctionRuleService.delete(entity.getId());
		assertNull(auctionRuleService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewAuctionRule();
		auctionRuleService.deleteAll();
		assertEquals(0, auctionRuleService.getAll().size());
	}

}

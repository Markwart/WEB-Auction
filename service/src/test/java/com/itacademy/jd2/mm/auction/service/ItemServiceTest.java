package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;

public class ItemServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final IItem entity = saveNewItem();

		final IItem entityFromDb = itemService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getAuctionEnd(), entityFromDb.getAuctionEnd());
		assertEquals(entity.getStartingPrice(), entityFromDb.getStartingPrice());
		assertEquals(entity.getCategory().getId(), entityFromDb.getCategory().getId());
		assertEquals(entity.getYear(), entityFromDb.getYear());
		assertEquals(entity.getCountryOrigin().getId(), entityFromDb.getCountryOrigin().getId());
		assertEquals(entity.getCondition().getId(), entityFromDb.getCondition().getId());
		assertEquals(entity.getComposition().getId(), entityFromDb.getComposition().getId());
		assertEquals(entity.getDuration().getId(), entityFromDb.getDuration().getId());
		assertEquals(entity.getImage(), entityFromDb.getImage());
		assertEquals(entity.getText(), entityFromDb.getText());
		assertEquals(entity.getSeller().getId(), entityFromDb.getSeller().getId());
		assertEquals(entity.getStatusAuction(), entityFromDb.getStatusAuction());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IItem entity = saveNewItem();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(DELAY);
		itemService.save(entity);

		final IItem entityFromDb = itemService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getAuctionEnd(), entityFromDb.getAuctionEnd());
		assertEquals(entity.getStartingPrice(), entityFromDb.getStartingPrice());
		assertEquals(entity.getCategory().getId(), entityFromDb.getCategory().getId());
		assertEquals(entity.getYear(), entityFromDb.getYear());
		assertEquals(entity.getCountryOrigin().getId(), entityFromDb.getCountryOrigin().getId());
		assertEquals(entity.getCondition().getId(), entityFromDb.getCondition().getId());
		assertEquals(entity.getComposition().getId(), entityFromDb.getComposition().getId());
		assertEquals(entity.getDuration().getId(), entityFromDb.getDuration().getId());
		assertEquals(entity.getImage(), entityFromDb.getImage());
		assertEquals(entity.getText(), entityFromDb.getText());
		assertEquals(entity.getSeller().getId(), entityFromDb.getSeller().getId());
		assertEquals(entity.getStatusAuction(), entityFromDb.getStatusAuction());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = itemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewItem();
		}

		final List<IItem> allEntities = itemService.getAll();

		for (final IItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getAuctionEnd());
			assertNotNull(entityFromDb.getStartingPrice());
			assertNotNull(entityFromDb.getCategory());
			assertNotNull(entityFromDb.getYear());
			assertNotNull(entityFromDb.getCountryOrigin());
			assertNotNull(entityFromDb.getCondition());
			assertNotNull(entityFromDb.getComposition());
			assertNotNull(entityFromDb.getDuration());
			assertNotNull(entityFromDb.getImage());
			assertNotNull(entityFromDb.getText());
			assertNotNull(entityFromDb.getSeller());
			assertNotNull(entityFromDb.getStatusAuction());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IItem entity = saveNewItem();
		itemService.delete(entity.getId());
		assertNull(itemService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewItem();
		itemService.deleteAll();
		assertEquals(0, itemService.getAll().size());
	}

}

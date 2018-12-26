package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;

public class FeedbackServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IFeedback entity = saveNewFeedback();

		final IFeedback entityFromDb = feedbackService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getCommunication(), entityFromDb.getCommunication());
		assertEquals(entity.getShippingTime(), entityFromDb.getShippingTime());
		assertEquals(entity.getShippingCharges(), entityFromDb.getShippingCharges());
		assertEquals(entity.getItemDescription(), entityFromDb.getItemDescription());
		assertEquals(entity.getComment(), entityFromDb.getComment());
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserFrom().getId(), entityFromDb.getUserFrom().getId());
		assertEquals(entity.getUserWhom().getId(), entityFromDb.getUserWhom().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IFeedback entity = saveNewFeedback();

		String newComment = entity.getComment() + "_updated";
		entity.setComment(newComment);
		Thread.sleep(DELAY);
		feedbackService.save(entity);

		final IFeedback entityFromDb = feedbackService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserFrom().getId(), entityFromDb.getUserFrom().getId());
		assertEquals(entity.getUserWhom().getId(), entityFromDb.getUserWhom().getId());
		assertEquals(entity.getCommunication(), entityFromDb.getCommunication());
		assertEquals(entity.getShippingTime(), entityFromDb.getShippingTime());
		assertEquals(entity.getShippingCharges(), entityFromDb.getShippingCharges());
		assertEquals(entity.getItemDescription(), entityFromDb.getItemDescription());
		assertEquals(entity.getComment(), entityFromDb.getComment());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		;
	}

	@Test
	public void testGetAll() {
		final int initialCount = feedbackService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewFeedback();
		}

		final List<IFeedback> allEntities = feedbackService.getAll();

		for (final IFeedback entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getItem());
			assertNotNull(entityFromDb.getUserFrom());
			assertNotNull(entityFromDb.getUserWhom());
			assertNotNull(entityFromDb.getCommunication());
			assertNotNull(entityFromDb.getShippingTime());
			assertNotNull( entityFromDb.getShippingCharges());
			assertNotNull( entityFromDb.getItemDescription());
			assertNotNull( entityFromDb.getComment());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IFeedback entity = saveNewFeedback();
		feedbackService.delete(entity.getId());
		assertNull(feedbackService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewFeedback();
		feedbackService.deleteAll();
		assertEquals(0, feedbackService.getAll().size());
	}
}

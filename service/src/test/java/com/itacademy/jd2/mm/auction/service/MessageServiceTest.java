package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;

public class MessageServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IMessage entity = saveNewMessage();

		final IMessage entityFromDb = messageService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getTheme(), entityFromDb.getTheme());
		assertEquals(entity.getText(), entityFromDb.getText());
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserAccountFrom().getId(), entityFromDb.getUserAccountFrom().getId());
		assertEquals(entity.getUserAccountWhom().getId(), entityFromDb.getUserAccountWhom().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IMessage entity = saveNewMessage();

		String newTheme = entity.getTheme() + "_updated";
		entity.setTheme(newTheme);
		Thread.sleep(DELAY);
		messageService.save(entity);

		final IMessage entityFromDb = messageService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getItem().getId(), entityFromDb.getItem().getId());
		assertEquals(entity.getUserAccountFrom().getId(), entityFromDb.getUserAccountFrom().getId());
		assertEquals(entity.getUserAccountWhom().getId(), entityFromDb.getUserAccountWhom().getId());
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
		final int initialCount = messageService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewMessage();
		}

		final List<IMessage> allEntities = messageService.getAll();

		for (final IMessage entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getItem().getId());
			assertNotNull(entityFromDb.getUserAccountFrom().getId());
			assertNotNull(entityFromDb.getUserAccountWhom().getId());
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
		final IMessage entity = saveNewMessage();
		messageService.delete(entity.getId());
		assertNull(messageService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewMessage();
		messageService.deleteAll();
		assertEquals(0, messageService.getAll().size());
	}
}

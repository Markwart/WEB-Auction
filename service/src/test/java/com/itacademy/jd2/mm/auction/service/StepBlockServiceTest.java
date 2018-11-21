package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;

public class StepBlockServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IStepBlock entity = saveNewStepBlock();

		final IStepBlock entityFromDb = stepBlockService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getStep_1(), entityFromDb.getStep_1());
		assertEquals(entity.getStep_2(), entityFromDb.getStep_2());
		assertEquals(entity.getStep_3(), entityFromDb.getStep_3());
		assertEquals(entity.getStep_4(), entityFromDb.getStep_4());
		assertEquals(entity.getStep_5(), entityFromDb.getStep_5());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}


	@Test
	public void testUpdate() throws InterruptedException {
		final IStepBlock entity = saveNewStepBlock();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		stepBlockService.save(entity);

		final IStepBlock entityFromDb = stepBlockService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getStep_1(), entityFromDb.getStep_1());
		assertEquals(entity.getStep_2(), entityFromDb.getStep_2());
		assertEquals(entity.getStep_3(), entityFromDb.getStep_3());
		assertEquals(entity.getStep_4(), entityFromDb.getStep_4());
		assertEquals(entity.getStep_5(), entityFromDb.getStep_5());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));;
	}

	@Test
	public void testGetAll() {
		final int initialCount = stepBlockService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewStepBlock();
		}

		final List<IStepBlock> allEntities = stepBlockService.getAll();

		for (final IStepBlock entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getStep_1());
			assertNotNull(entityFromDb.getStep_2());
			assertNotNull(entityFromDb.getStep_3());
			assertNotNull(entityFromDb.getStep_4());
			assertNotNull(entityFromDb.getStep_5());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}
		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IStepBlock entity = saveNewStepBlock();
		stepBlockService.delete(entity.getId());
		assertNull(stepBlockService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewStepBlock();
		stepBlockService.deleteAll();
		assertEquals(0, stepBlockService.getAll().size());
	}

}

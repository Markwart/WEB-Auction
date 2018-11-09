package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;

public class ConditionServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final ICondition entity = saveNewCondition();

        final ICondition entityFromDb = conditionService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getName(), entityFromDb.getName());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
    
    @Test
    public void testUpdate() throws InterruptedException {
        final ICondition entity = saveNewCondition();

        String newName = entity.getName() + "_updated";
        entity.setName(newName);
        Thread.sleep(2000);
        conditionService.save(entity);

        final ICondition entityFromDb = conditionService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(newName, entityFromDb.getName());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertEquals(entity.getCreated(), entityFromDb.getCreated());
        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
    }

    @Test
    public void testGetAll() {
        final int initialCount = conditionService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCondition();
        }

        final List<ICondition> allEntities = conditionService.getAll();

        for (final ICondition entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getName());
            assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final ICondition entity = saveNewCondition();
        conditionService.delete(entity.getId());
        assertNull(conditionService.get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewCondition();
        conditionService.deleteAll();
        assertEquals(0, conditionService.getAll().size());
    }
    
    /*   @Test
    public void testCreateMultiple() {
        int initialSize = conditionService.getAll().size();

        final ICondition entity1 = conditionService.createEntity();
        entity1.setName("condition-" + getRandomPrefix());

        try {
            final ICondition entity2 = conditionService.createEntity();
            conditionService.save(entity1, entity2);
            fail("Condition save should fail if name not specified");
        } catch (Exception e) {
            assertEquals(initialSize, conditionService.getAll().size());
        }
    }*/
}

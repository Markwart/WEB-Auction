package com.itacademy.jd2.mm.auction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;

public class AdminCommunicationServiceTest extends AbstractTest{
	
	@Test
    public void testCreate() {
        final IAdminCommunication entity = saveNewAdminCommunication();

        final IAdminCommunication entityFromDb = adminCommunicationService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getTheme(), entityFromDb.getTheme());
        assertEquals(entity.getText(), entityFromDb.getText());
        assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
	
	 @Test
	    public void testUpdate() throws InterruptedException {
	        final IAdminCommunication entity = saveNewAdminCommunication();

	        String newTheme = entity.getTheme() + "_updated";
	        entity.setTheme(newTheme);
	        Thread.sleep(2000);
	        adminCommunicationService.save(entity);

	        final IAdminCommunication entityFromDb = adminCommunicationService.get(entity.getId());

	        assertNotNull(entityFromDb);
	        assertEquals(newTheme, entityFromDb.getTheme());
	        assertEquals(entity.getText(), entityFromDb.getText());
	        assertEquals(entity.getUserAccount().getId(), entityFromDb.getUserAccount().getId());
	        assertNotNull(entityFromDb.getId());
	        assertNotNull(entityFromDb.getCreated());
	        assertNotNull(entityFromDb.getUpdated());
	        assertEquals(entity.getCreated(), entityFromDb.getCreated());
	        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	    }
	 
	  @Test
	    public void testGetAll() {
	        final int initialCount = adminCommunicationService.getAll().size();

	        final int randomObjectsCount = getRandomObjectsCount();
	        for (int i = 0; i < randomObjectsCount; i++) {
	            saveNewAdminCommunication();
	        }

	        final List<IAdminCommunication> allEntities = adminCommunicationService.getAll();

	        for (final IAdminCommunication entityFromDb : allEntities) {
	            assertNotNull(entityFromDb.getTheme());
	            assertNotNull(entityFromDb.getText());
	            assertNotNull(entityFromDb.getUserAccount().getId());
	            assertNotNull(entityFromDb.getId());
	            assertNotNull(entityFromDb.getCreated());
	            assertNotNull(entityFromDb.getUpdated());
	        }

	        assertEquals(randomObjectsCount + initialCount, allEntities.size());
	    }
	  
	  @Test
	    public void testDelete() {
	        final IAdminCommunication entity = saveNewAdminCommunication();
	        adminCommunicationService.delete(entity.getId());
	        assertNull(adminCommunicationService.get(entity.getId()));
	    }
	   
	   @Test
	    public void testDeleteAll() {
	        saveNewAdminCommunication();
	        adminCommunicationService.deleteAll();
	        assertEquals(0, adminCommunicationService.getAll().size());
	    }

}

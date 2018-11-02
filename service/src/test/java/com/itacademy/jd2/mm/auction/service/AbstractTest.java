package com.itacademy.jd2.mm.auction.service;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.impl.CompositionServiceImpl;
import com.itacademy.jd2.mm.auction.service.impl.ConditionServiceImpl;

public abstract class AbstractTest {
	
    protected IConditionService conditionService = new ConditionServiceImpl();
    protected ICompositionService compositionService = new CompositionServiceImpl();

    private static final Random RANDOM = new Random();

    @BeforeEach
    public void setUpMethod() {
        // clean DB recursive
        conditionService.deleteAll();
        compositionService.deleteAll();
        
    }

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    protected ICondition saveNewCondition() {
        final ICondition entity = conditionService.createEntity();
        entity.setName("condition-" + getRandomPrefix());
        conditionService.save(entity);
        return entity;
    }
    
    protected IComposition saveNewComposition() {
    	final IComposition entity = compositionService.createEntity();
    	entity.setName("composition-" + getRandomPrefix());
    	compositionService.save(entity);
		return entity;}

}

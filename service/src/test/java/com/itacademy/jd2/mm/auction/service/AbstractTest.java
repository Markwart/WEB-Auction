package com.itacademy.jd2.mm.auction.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.service.IConditionService;

@SpringJUnitConfig(locations = "classpath:service-context.xml")
public abstract class AbstractTest {

	@Autowired
	protected IConditionService conditionService;
	@Autowired
	protected ICompositionService compositionService;
	@Autowired
	protected ICategoryService categoryService;
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected IAdminCommunicationService adminCommunicationService;
	@Autowired
	protected ICountryOriginService countryOriginService;
	@Autowired
	protected IPaymentMethodService paymentMethodService;
	@Autowired
	protected IShippingMethodService shippingMethodService;
	@Autowired
	protected IMessageService messageService;
	@Autowired
	protected IItemService itemService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		messageService.deleteAll();
		
		itemService.deleteAll();
		adminCommunicationService.deleteAll();
		
		conditionService.deleteAll();
		categoryService.deleteAll();
		compositionService.deleteAll();
		countryOriginService.deleteAll();
		paymentMethodService.deleteAll();
		shippingMethodService.deleteAll();
		userAccountService.deleteAll();

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
		return entity;
	}

	protected ICategory saveNewCategory() {
		final ICategory entity = categoryService.createEntity();
		entity.setName("category-" + getRandomPrefix());
		categoryService.save(entity);
		return entity;
	}
	
	protected ICountryOrigin saveNewCountryOrigin() {
		final ICountryOrigin entity = countryOriginService.createEntity();
		entity.setName("countryOrigin-" + getRandomPrefix());
		countryOriginService.save(entity);
		return entity;
	}
	
	protected IPaymentMethod saveNewPaymentMethod() {
		final IPaymentMethod entity = paymentMethodService.createEntity();
		entity.setName("paymentMethod-" + getRandomPrefix());
		paymentMethodService.save(entity);
		return entity;
	}
	
	protected IShippingMethod saveNewShippingMethod() {
		final IShippingMethod entity = shippingMethodService.createEntity();
		entity.setName("shipping_method-" + getRandomPrefix());
		entity.setDeliveryTime("delivey_time-" + getRandomPrefix());
		entity.setCost(new BigDecimal(getRANDOM().nextInt(10000)));
		shippingMethodService.save(entity);
		return entity;
	}
	
	protected IMessage saveNewMessage() {
		final IMessage entity = messageService.createEntity();
		entity.setTheme("theme-" + getRandomPrefix());
		entity.setText("text-" + getRandomPrefix());
		entity.setItem(saveNewItem());
		messageService.save(entity);
		return entity;
	}
	
	protected IItem saveNewItem() {
		final IItem entity = itemService.createEntity();
		entity.setName("name" + getRandomPrefix());
		entity.setAuctionEnd(new Date());
		entity.setStartingPrice(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setCategory(saveNewCategory());
		entity.setYear(getRANDOM().nextInt(2018));
		entity.setCountryOrigin(saveNewCountryOrigin());
		entity.setCondition(saveNewCondition());
		entity.setComposition(saveNewComposition());
		entity.setImage("mindmap.jpg");
		entity.setText("text" + getRandomPrefix());
		entity.setSeller(saveNewUserAccount());
		entity.setStatusAuction("status_auction" + getRandomPrefix());
		itemService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setRole(getRANDOM().nextInt(2));
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		userAccountService.save(entity);
		return entity;
	}

	protected IAdminCommunication saveNewAdminCommunication() {
		final IAdminCommunication entity = adminCommunicationService.createEntity();
		entity.setTheme("theme-" + getRandomPrefix());
		entity.setText("text-" + getRandomPrefix());
		entity.setUserAccount(saveNewUserAccount());
		adminCommunicationService.save(entity);
		return entity;
	}
}

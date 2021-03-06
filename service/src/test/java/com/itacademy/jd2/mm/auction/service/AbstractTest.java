package com.itacademy.jd2.mm.auction.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {

	public static final Integer DELAY = 1000;
	
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
	@Autowired
	protected IBidService bidService;
	@Autowired
	protected IDeferredBidService deferredBidService;
	@Autowired
	protected IFeedbackService feedbackService;
	@Autowired
	protected IAuctionRuleService auctionRuleService;
	@Autowired
	protected IAuctionDurationService auctionDurationService;
	@Autowired
	protected IStepBlockService stepBlockService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		messageService.deleteAll();
		bidService.deleteAll();
		deferredBidService.deleteAll();
		feedbackService.deleteAll();

		itemService.deleteAll();
		adminCommunicationService.deleteAll();

		conditionService.deleteAll();
		categoryService.deleteAll();
		compositionService.deleteAll();
		countryOriginService.deleteAll();
		paymentMethodService.deleteAll();
		shippingMethodService.deleteAll();
		auctionRuleService.deleteAll();
		auctionDurationService.deleteAll();
		stepBlockService.deleteAll();
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

	protected IAuctionRule saveNewAuctionRule() {
		final IAuctionRule entity = auctionRuleService.createEntity();
		entity.setIndex("index-" + getRandomPrefix());
		entity.setTheme("theme-" + getRandomPrefix());
		entity.setText("text-" + getRandomPrefix());
		auctionRuleService.save(entity);
		return entity;
	}

	protected IAuctionDuration saveNewAuctionDuration() {
		final IAuctionDuration entity = auctionDurationService.createEntity();
		entity.setDay(RANDOM.nextInt(1000));
		auctionDurationService.save(entity);
		return entity;
	}

	protected IStepBlock saveNewStepBlock() {
		final IStepBlock entity = stepBlockService.createEntity();
		entity.setName("step_block-" + getRandomPrefix());
		entity.setStep_1(getRandomObjectsCount());
		entity.setStep_2(getRandomObjectsCount());
		entity.setStep_3(getRandomObjectsCount());
		entity.setStep_4(getRandomObjectsCount());
		entity.setStep_5(getRandomObjectsCount());
		stepBlockService.save(entity);
		return entity;
	}

	protected IMessage saveNewMessage() {
		final IMessage entity = messageService.createEntity();
		entity.setTheme("theme-" + getRandomPrefix());
		entity.setText("text-" + getRandomPrefix());
		entity.setItem(saveNewItem());
		entity.setUserFrom(saveNewUserAccount());
		entity.setUserWhom(saveNewUserAccount());
		messageService.save(entity, null, null);
		return entity;
	}

	protected IFeedback saveNewFeedback() {
		final IFeedback entity = feedbackService.createEntity();
		entity.setCommunication(RANDOM.nextInt(5) + 1);
		entity.setShippingTime(RANDOM.nextInt(5) + 1);
		entity.setShippingCharges(RANDOM.nextInt(5) + 1);
		entity.setItemDescription(RANDOM.nextInt(5) + 1);
		entity.setComment("comment-" + getRandomPrefix());
		entity.setItem(saveNewItem());
		entity.setUserFrom(saveNewUserAccount());
		entity.setUserWhom(saveNewUserAccount());
		feedbackService.save(entity);
		return entity;
	}

	protected IBid saveNewBid() {
		final IBid entity = bidService.createEntity();
		entity.setPriceBid(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setStatusBid(StatusBid.made);
		entity.setItem(saveNewItem());
		entity.setUserBid(saveNewUserAccount());
		bidService.save(entity, null, null);
		return entity;
	}

	protected IDeferredBid saveNewDeferredBid() {
		final IDeferredBid entity = deferredBidService.createEntity();
		entity.setPriceBid(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setStatusBid(StatusBid.outbidded);
		entity.setItem(saveNewItem());
		entity.setUserBid(saveNewUserAccount());
		deferredBidService.save(entity);
		return entity;
	}

	protected IItem saveNewItem() {
		final IItem entity = itemService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setAuctionEnd(new Date());
		entity.setStartingPrice(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setCategory(saveNewCategory());
		entity.setYear(getRANDOM().nextInt(2018));
		entity.setCountryOrigin(saveNewCountryOrigin());
		entity.setCondition(saveNewCondition());
		entity.setComposition(saveNewComposition());
		entity.setDuration(saveNewAuctionDuration());
		entity.setImage("mindmap.jpg");
		entity.setText("text-" + getRandomPrefix());
		entity.setSeller(saveNewUserAccount());
		entity.setStatusAuction(StatusAuction.OPEN);
		
		try {
			itemService.save(entity, null, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		final IPersonalData personalDataEntity = userAccountService.createPersonalDataEntity();
		entity.setRole(Roles.user);
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		personalDataEntity.setFirstName("first_name-" + getRandomPrefix());
		personalDataEntity.setLastName("last_name-" + getRandomPrefix());
		personalDataEntity.setCountry("country-" + getRandomPrefix());
		personalDataEntity.setCity("city-" + getRandomPrefix());
		personalDataEntity.setAdress("adress-" + getRandomPrefix());
		personalDataEntity.setUsername("username-" + getRandomPrefix());
		personalDataEntity.setId(entity.getId());
		entity.setPersonalData(personalDataEntity);
		userAccountService.save(entity, personalDataEntity);
		return entity;
	}

	protected IAdminCommunication saveNewAdminCommunication() {
		final IAdminCommunication entity = adminCommunicationService.createEntity();
		entity.setTheme("theme-" + getRandomPrefix());
		entity.setText("text-" + getRandomPrefix());
		entity.setUserFrom(saveNewUserAccount());
		adminCommunicationService.save(entity);
		return entity;
	}
}

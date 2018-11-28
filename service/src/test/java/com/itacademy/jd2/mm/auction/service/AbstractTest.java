package com.itacademy.jd2.mm.auction.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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
import com.itacademy.jd2.mm.auction.service.IConditionService;

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
	@Autowired
	protected IPersonalDataService personalDataService;

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
		personalDataService.deleteAll();

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
		entity.setMin(RANDOM.nextInt(10000));
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
		entity.setUserAccountFrom(saveNewUserAccount());
		entity.setUserAccountWhom(saveNewUserAccount());
		messageService.save(entity);
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
		entity.setUserAccountFrom(saveNewUserAccount());
		entity.setUserAccountWhom(saveNewUserAccount());
		feedbackService.save(entity);
		return entity;
	}

	protected IBid saveNewBid() {
		final IBid entity = bidService.createEntity();
		entity.setPriceBid(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setStatusBid("status_bid-" + getRandomPrefix());
		entity.setItem(saveNewItem());
		entity.setUserAccount(saveNewUserAccount());
		bidService.save(entity);
		return entity;
	}

	protected IDeferredBid saveNewDeferredBid() {
		final IDeferredBid entity = deferredBidService.createEntity();
		entity.setPriceBid(new BigDecimal(getRANDOM().nextInt(10000)));
		entity.setStatusBid("status_bid-" + getRandomPrefix());
		entity.setItem(saveNewItem());
		entity.setUserAccount(saveNewUserAccount());
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
		entity.setImage("mindmap.jpg");
		entity.setText("text-" + getRandomPrefix());
		entity.setSeller(saveNewUserAccount());
		entity.setStatusAuction("status_auction-" + getRandomPrefix());
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

	protected IPersonalData saveNewPersonalData() {
		final IPersonalData entity = personalDataService.createEntity();
		entity.setUserName("username-" + getRandomPrefix());
		entity.setFirstName("first_name-" + getRandomPrefix());
		entity.setLastName("last_name-" + getRandomPrefix());
		entity.setAdress("adress-" + getRandomPrefix());
		entity.setId(saveNewUserAccount().getId());
		personalDataService.save(entity);
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

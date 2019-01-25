package com.itacademy.jd2.mm.auction.web.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;
import com.itacademy.jd2.mm.auction.service.IAuctionDurationService;
import com.itacademy.jd2.mm.auction.service.IBidService;
import com.itacademy.jd2.mm.auction.service.ICategoryService;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IPaymentMethodService;
import com.itacademy.jd2.mm.auction.service.IShippingMethodService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.ItemFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.ItemToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.BidDTO;
import com.itacademy.jd2.mm.auction.web.dto.ItemDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.mm.auction.web.dto.search.ItemSearchDTO;
import com.itacademy.jd2.mm.auction.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = ItemController.class.getSimpleName() + "_SEACH_DTO";

	private IItemService itemService;
	private IUserAccountService userAccountService;
	private ICategoryService categoryService;
	private IConditionService conditionService;
	private ICompositionService compositionService;
	private ICountryOriginService countryOriginService;
	private IAuctionDurationService auctionDurationService;

	private ItemToDTOConverter toDtoConverter;
	private ItemFromDTOConverter fromDtoConverter;

	@Autowired
	private IShippingMethodService shippingMethodService;
	@Autowired
	private IPaymentMethodService paymentMethodService;
	@Autowired
	private IBidService bidService;

	@Autowired
	public ItemController(IItemService itemService, IUserAccountService userAccountService,
			ICategoryService categoryService, IConditionService conditionService,
			ICompositionService compositionService, ICountryOriginService countryOriginService,
			ItemToDTOConverter toDtoConverter, ItemFromDTOConverter fromDtoConverter,
			IAuctionDurationService auctionDurationService) {
		super();
		this.itemService = itemService;
		this.userAccountService = userAccountService;
		this.categoryService = categoryService;
		this.conditionService = conditionService;
		this.compositionService = compositionService;
		this.countryOriginService = countryOriginService;
		this.auctionDurationService = auctionDurationService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
		;
	}

	@RequestMapping(value = { "", "/private", "/userItems/{userId}" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) ItemSearchDTO searchDTO,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn,
			@PathVariable(name = "userId", required = false) final Integer userId) {

		Integer loggedUserId = AuthHelper.getLoggedUserId();
		boolean isPrivate = req.getRequestURI().contains("/private");
		boolean isPrivateUserId = req.getRequestURI().matches(".*\\/userItems\\/[0-9]*");

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			searchDTO = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}

		final ItemFilter filter = new ItemFilter();
		filter.setFetchUserAccount(true);
		filter.setFetchCategory(true);
		filter.setFetchCondition(true);
		filter.setFetchComposition(true);
		filter.setFetchCountryOrigin(true);
		filter.setFetchAuctionDuration(true);

		if (isPrivate) {
			filter.setLoggedUserId(loggedUserId); // get private Item list
		} else if (isPrivateUserId) {
			filter.setLoggedUserId(userId); // get user private Item list
		} else {
			filter.setLoggedUserId(loggedUserId = null); // get all list
		}

		prepareFilter(gridState, filter);
		gridState.setTotalCount(itemService.getCount(filter));

		List<IItem> entities;
		if (!StringUtils.isEmpty(searchDTO.getName())) {
			filter.setName(searchDTO.getName());
			entities = itemService.findInIndex(filter.getName());
		} else {
			entities = itemService.find(filter);
		}
		List<ItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDTO);
		models.put("showSomeElements", isPrivate);
		return new ModelAndView("item.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new ItemDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ItemDTO formModel,
			@RequestParam("file") final MultipartFile file, final BindingResult result) throws IOException {

		String uuid = UUID.randomUUID().toString();
		LOGGER.debug("Uploaded file %s", file.getOriginalFilename());

		Integer loggedUserId = AuthHelper.getLoggedUserId();

		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("item.edit", hashMap);
		} else {
			final IItem entity = fromDtoConverter.apply(formModel);
			entity.setImage(uuid);
			itemService.save(entity, loggedUserId, uuid, file);
			return "redirect:/item";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		itemService.delete(id);
		return "redirect:/item";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		Integer loggedUserId = AuthHelper.getLoggedUserId();
		final BidFilter bidFilter = new BidFilter();
		bidFilter.setItemId(id);

		final IItem dbModel = itemService.getFullInfo(id);
		final ItemDTO dto = toDtoConverter.apply(dbModel);
		
		final IUserAccount userAccount = userAccountService
				.getPersonalData(itemService.getFullInfo(id).getSeller().getId());
		
		//List<IBid> bidEntities = bidService.getBidByItemId(id);
		dto.setTotalCountBids(bidService.getCountItemBids(bidFilter));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formView", dto);
		hashMap.put("formBid", new BidDTO());
		hashMap.put("showSomeElements", loggedUserId);
		hashMap.put("userAccountData", userAccount);
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.view", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ItemDTO dto = toDtoConverter.apply(itemService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccountsList = userAccountService.getAll();
		final List<ICategory> categoryList = categoryService.getAll();
		final List<ICondition> conditionList = conditionService.getAll();
		final List<IComposition> compositionList = compositionService.getAll();
		final List<ICountryOrigin> countryOriginList = countryOriginService.getAll();
		final List<StatusAuction> statusAuctionList = Arrays.asList(StatusAuction.values());
		final List<IAuctionDuration> auctionDurationList = auctionDurationService.getAll();

		/*
		 * final Map<Integer, String> userAccountsMap = new HashMap<>(); for (final
		 * IUserAccount iUserAccount : userAccounts) {
		 * userAccountsMap.put(iUserAccount.getId(), iUserAccount.getEmail()); }
		 */

		final Map<Integer, String> userAccountsMap = userAccountsList.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("sellerChoices", userAccountsMap);

		final Map<Integer, String> categoryMap = categoryList.stream()
				.collect(Collectors.toMap(ICategory::getId, ICategory::getName));
		hashMap.put("categoryChoices", categoryMap);

		final Map<Integer, String> conditionMap = conditionList.stream()
				.collect(Collectors.toMap(ICondition::getId, ICondition::getName));
		hashMap.put("conditionChoices", conditionMap);

		final Map<Integer, String> compositionMap = compositionList.stream()
				.collect(Collectors.toMap(IComposition::getId, IComposition::getName));
		hashMap.put("compositionChoices", compositionMap);

		final Map<Integer, String> countryOriginMap = countryOriginList.stream()
				.collect(Collectors.toMap(ICountryOrigin::getId, ICountryOrigin::getName));
		hashMap.put("countryOriginChoices", countryOriginMap);

		final Map<String, String> statusAuctionMap = statusAuctionList.stream()
				.collect(Collectors.toMap(StatusAuction::name, StatusAuction::name));
		hashMap.put("statusAuctionChoices", statusAuctionMap);

		final Map<Integer, Integer> auctionDurationMap = auctionDurationList.stream()
				.collect(Collectors.toMap(IAuctionDuration::getId, IAuctionDuration::getDay));
		hashMap.put("auctionDurationChoices", auctionDurationMap);

		final Map<Integer, String> shippingMethodsMap = shippingMethodService.getAll().stream()
				.collect(Collectors.toMap(IShippingMethod::getId, IShippingMethod::getName));
		hashMap.put("shippingMethodChoices", shippingMethodsMap);

		final Map<Integer, String> paymentMethodsMap = paymentMethodService.getAll().stream()
				.collect(Collectors.toMap(IPaymentMethod::getId, IPaymentMethod::getName));
		hashMap.put("paymentMethodChoices", paymentMethodsMap);
	}

	private ItemSearchDTO getSearchDTO(final HttpServletRequest req) {
		ItemSearchDTO searchDTO = (ItemSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new ItemSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}

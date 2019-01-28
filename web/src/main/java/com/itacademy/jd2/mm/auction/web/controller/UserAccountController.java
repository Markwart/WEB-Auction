package com.itacademy.jd2.mm.auction.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.UserAccountDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.mm.auction.web.dto.search.UserAccountSearchDTO;
import com.itacademy.jd2.mm.auction.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController {

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = UserAccountController.class.getSimpleName() + "_SEACH_DTO";

	private IUserAccountService userAccountService;

	private UserAccountToDTOConverter toDtoConverter;
	private UserAccountFromDTOConverter fromDtoConverter;

	@Autowired
	private IItemService itemService;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountToDTOConverter toDtoConverter,
			UserAccountFromDTOConverter fromDtoConverter) {
		super();
		this.userAccountService = userAccountService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req,
			@ModelAttribute(SEARCH_FORM_MODEL) UserAccountSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		boolean isRequestMethodPost = req.getMethod().equalsIgnoreCase("post");

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (!isRequestMethodPost) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final UserAccountFilter filter = new UserAccountFilter();
		if (searchDto.getEmail() != null) {
			filter.setEmail(searchDto.getEmail());
		}

		if (!isRequestMethodPost) {
			prepareFilter(gridState, filter); // get view without sort and paging
		}

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDto);
		models.put("showPagingAndSort", !isRequestMethodPost);
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new UserAccountDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return "userAccount.edit";
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			final IPersonalData personalDataEntity = entity.getPersonalData();
			userAccountService.save(entity, personalDataEntity);
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.getPersonalData(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.getPersonalData(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = { "/{itemId}/addWatchList", "/{itemId}/removeWatchList", "/{itemId}/addWatchList/main",
			"/{itemId}/removeWatchList/main" }, method = RequestMethod.GET)
	public Object WatchList(@PathVariable(name = "itemId", required = true) final Integer itemId,
			final HttpServletRequest req) {

		Integer loggedUserId = AuthHelper.getLoggedUserId();

		IUserAccount userAccount = userAccountService.getPersonalData(loggedUserId);
		if (req.getRequestURI().contains("/addWatchList")) {
			userAccount.getItems().add(itemService.get(itemId));
		} else {
			Set<IItem> items = new HashSet<>();
			for (IItem iItem : userAccount.getItems()) {
				if (!iItem.getId().equals(itemId)) {
					items.add(iItem);
				}
			}
			userAccount.setItems(items);
		}
		userAccountService.update(userAccount);
		if (req.getRequestURI().contains("/main")) {
			return "redirect:/item";
		} else {
			return "redirect:/item/{itemId}";
		}
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<Roles> rolesList = Arrays.asList(Roles.values());

		final Map<String, String> rolesMap = rolesList.stream().collect(Collectors.toMap(Roles::name, Roles::name));
		hashMap.put("rolesChoices", rolesMap);

		final Map<Integer, String> itemsMap = itemService.getAll().stream()
				.collect(Collectors.toMap(IItem::getId, IItem::getName));
		hashMap.put("itemsChoices", itemsMap);
	}

	private UserAccountSearchDTO getSearchDTO(final HttpServletRequest req) {
		UserAccountSearchDTO searchDTO = (UserAccountSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new UserAccountSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}

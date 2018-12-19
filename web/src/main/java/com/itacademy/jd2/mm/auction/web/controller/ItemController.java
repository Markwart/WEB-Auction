package com.itacademy.jd2.mm.auction.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;
import com.itacademy.jd2.mm.auction.service.ICategoryService;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.ItemFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.ItemToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.ItemDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractController {

	private IItemService itemService;
	private IUserAccountService userAccountService;
	private ICategoryService categoryService;
	private IConditionService conditionService;
	private ICompositionService compositionService;
	private ICountryOriginService countryOriginService;

	private ItemToDTOConverter toDtoConverter;
	private ItemFromDTOConverter fromDtoConverter;

	@Autowired
	public ItemController(IItemService itemService, IUserAccountService userAccountService,
			ICategoryService categoryService, IConditionService conditionService,
			ICompositionService compositionService, ICountryOriginService countryOriginService,
			ItemToDTOConverter toDtoConverter, ItemFromDTOConverter fromDtoConverter) {
		super();
		this.itemService = itemService;
		this.userAccountService = userAccountService;
		this.categoryService = categoryService;
		this.conditionService = conditionService;
		this.compositionService = compositionService;
		this.countryOriginService = countryOriginService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ItemFilter filter = new ItemFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(itemService.getCount(filter));

		filter.setFetchUserAccount(true);
		final List<IItem> entities = itemService.find(filter);
		List<ItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("item.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new ItemDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ItemDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("item.edit", hashMap);
		} else {
			final IItem entity = fromDtoConverter.apply(formModel);
			itemService.save(entity);
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
		final IItem dbModel = itemService.get(id);
		final ItemDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ItemDTO dto = toDtoConverter.apply(itemService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("item.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();
		final List<ICategory> category = categoryService.getAll();
		final List<ICondition> condition = conditionService.getAll();
		final List<IComposition> composition = compositionService.getAll();
		final List<ICountryOrigin> countryOrigin = countryOriginService.getAll();

		/*
		 * final Map<Integer, String> userAccountsMap = new HashMap<>(); for (final
		 * IUserAccount iUserAccount : userAccounts) {
		 * userAccountsMap.put(iUserAccount.getId(), iUserAccount.getEmail()); }
		 */

		final Map<Integer, String> userAccountsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("sellerChoices", userAccountsMap);
		
		final Map<Integer, String> categoryMap = category.stream()
				.collect(Collectors.toMap(ICategory::getId, ICategory::getName));
		hashMap.put("categoryChoices", categoryMap);
		
		final Map<Integer, String> conditionMap = condition.stream()
				.collect(Collectors.toMap(ICondition::getId, ICondition::getName));
		hashMap.put("conditionChoices", conditionMap);
		
		final Map<Integer, String> compositionMap = composition.stream()
				.collect(Collectors.toMap(IComposition::getId, IComposition::getName));
		hashMap.put("compositionChoices", compositionMap);
		
		final Map<Integer, String> countryOriginMap = countryOrigin.stream()
				.collect(Collectors.toMap(ICountryOrigin::getId, ICountryOrigin::getName));
		hashMap.put("countryOriginChoices", countryOriginMap);
	}
}
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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;
import com.itacademy.jd2.mm.auction.service.IBidService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.BidFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.BidToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.BidDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/bid")
public class BidController extends AbstractController {

	private IBidService bidService;
	private IUserAccountService userAccountService;
	private IItemService itemService;

	private BidToDTOConverter toDtoConverter;
	private BidFromDTOConverter fromDtoConverter;

	@Autowired
	public BidController(IBidService bidService, IUserAccountService userAccountService, IItemService itemService,
			BidToDTOConverter toDtoConverter, BidFromDTOConverter fromDtoConverter) {
		super();
		this.bidService = bidService;
		this.userAccountService = userAccountService;
		this.itemService = itemService;
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

		final BidFilter filter = new BidFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(bidService.getCount(filter));

		filter.setFetchUserAccount(true);
		filter.setFetchItem(true);
		
		final List<IBid> entities = bidService.find(filter);
		List<BidDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("bid.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new BidDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("bid.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final BidDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("bid.edit", hashMap);
		} else {
			final IBid entity = fromDtoConverter.apply(formModel);
			bidService.save(entity);
			return "redirect:/bid"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		bidService.delete(id);
		return "redirect:/bid";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBid dbModel = bidService.get(id);
		final BidDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("bid.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BidDTO dto = toDtoConverter.apply(bidService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("bid.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();
		final List<IItem> items = itemService.getAll();

		/*
		 * final Map<Integer, String> userAccountsMap = new HashMap<>(); for (final
		 * IUserAccount iUserAccount : userAccounts) {
		 * userAccountsMap.put(iUserAccount.getId(), iUserAccount.getEmail()); }
		 */

		final Map<Integer, String> userAccountsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountsChoices", userAccountsMap);

		final Map<Integer, String> itemsMap = items.stream()
				.collect(Collectors.toMap(IItem::getId, IItem::getName));
		hashMap.put("itemsChoices", itemsMap);
	}
}

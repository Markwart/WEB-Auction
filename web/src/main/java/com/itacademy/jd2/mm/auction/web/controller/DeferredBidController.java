package com.itacademy.jd2.mm.auction.web.controller;

import java.util.Arrays;
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

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;
import com.itacademy.jd2.mm.auction.service.IDeferredBidService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.DeferredBidFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.DeferredBidToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.BidDTO;
import com.itacademy.jd2.mm.auction.web.dto.DeferredBidDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/deferredBid")
public class DeferredBidController extends AbstractController {

	private IDeferredBidService deferredBidService;
	private IUserAccountService userAccountService;
	private IItemService itemService;

	private DeferredBidToDTOConverter toDtoConverter;
	private DeferredBidFromDTOConverter fromDtoConverter;

	@Autowired
	public DeferredBidController(IDeferredBidService deferredBidService, IUserAccountService userAccountService, IItemService itemService,
			DeferredBidToDTOConverter toDtoConverter, DeferredBidFromDTOConverter fromDtoConverter) {
		super();
		this.deferredBidService = deferredBidService;
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

		final DeferredBidFilter filter = new DeferredBidFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(deferredBidService.getCount(filter));

		filter.setFetchUserAccount(true);
		filter.setFetchItem(true);
		final List<IDeferredBid> entities = deferredBidService.find(filter);
		List<DeferredBidDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("deferredBid.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new BidDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("deferredBid.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final DeferredBidDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("deferredBid.edit", hashMap);
		} else {
			final IDeferredBid entity = fromDtoConverter.apply(formModel);
			deferredBidService.save(entity);
			return "redirect:/deferredBid"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		deferredBidService.delete(id);
		return "redirect:/deferredBid";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IDeferredBid dbModel = deferredBidService.getFullInfo(id);
		final DeferredBidDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("deferredBid.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final DeferredBidDTO dto = toDtoConverter.apply(deferredBidService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("deferredBid.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();
		final List<IItem> items = itemService.getAll();
		final List<StatusBid> statusBidList = Arrays.asList(StatusBid.values());

		final Map<Integer, String> userAccountsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountsChoices", userAccountsMap);

		final Map<Integer, String> itemsMap = items.stream()
				.collect(Collectors.toMap(IItem::getId, IItem::getName));
		hashMap.put("itemsChoices", itemsMap);
		
		final Map<String, String> statusBidMap = statusBidList.stream()
				.collect(Collectors.toMap(StatusBid::name, StatusBid::name));
		hashMap.put("statusBidChoices", statusBidMap);
	}
}

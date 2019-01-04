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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;
import com.itacademy.jd2.mm.auction.service.IAdminCommunicationService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.AdminCommunicationFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.AdminCommunicationToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.AdminCommunicationDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/adminCommunication")
public class AdminCommunicationController extends AbstractController {

	private IAdminCommunicationService adminCommunicationService;
	private IUserAccountService userAccountService;

	private AdminCommunicationToDTOConverter toDtoConverter;
	private AdminCommunicationFromDTOConverter fromDtoConverter;

	@Autowired
	public AdminCommunicationController(IAdminCommunicationService adminCommunicationService,
			IUserAccountService userAccountService, AdminCommunicationToDTOConverter toDtoConverter,
			AdminCommunicationFromDTOConverter fromDtoConverter) {
		super();
		this.adminCommunicationService = adminCommunicationService;
		this.userAccountService = userAccountService;
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

		final AdminCommunicationFilter filter = new AdminCommunicationFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(adminCommunicationService.getCount(filter));

		filter.setFetchUserAccount(true);
		final List<IAdminCommunication> entities = adminCommunicationService.find(filter);
		List<AdminCommunicationDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("adminCommunication.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new AdminCommunicationDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("adminCommunication.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final AdminCommunicationDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("adminCommunication.edit", hashMap);
		} else {
			final IAdminCommunication entity = fromDtoConverter.apply(formModel);
			adminCommunicationService.save(entity);
			return "redirect:/adminCommunication";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		adminCommunicationService.delete(id);
		return "redirect:/adminCommunication";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IAdminCommunication dbModel = adminCommunicationService.getFullInfo(id);
		final AdminCommunicationDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("adminCommunication.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AdminCommunicationDTO dto = toDtoConverter.apply(adminCommunicationService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("adminCommunication.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();

		final Map<Integer, String> userAccountsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountsChoices", userAccountsMap);

	}
}

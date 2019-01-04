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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;
import com.itacademy.jd2.mm.auction.service.IFeedbackService;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.FeedbackFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.FeedbackToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.FeedbackDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController extends AbstractController {

	private IFeedbackService feedbackService;
	private IUserAccountService userAccountService;
	private IItemService itemService;

	private FeedbackToDTOConverter toDtoConverter;
	private FeedbackFromDTOConverter fromDtoConverter;

	@Autowired
	public FeedbackController(IFeedbackService feedbackService, IUserAccountService userAccountService,
			IItemService itemService, FeedbackToDTOConverter toDtoConverter,
			FeedbackFromDTOConverter fromDtoConverter) {
		super();
		this.feedbackService = feedbackService;
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

		final FeedbackFilter filter = new FeedbackFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(feedbackService.getCount(filter));

		filter.setFetchUserAccountFrom(true);
		filter.setFetchUserAccountWhom(true);
		filter.setFetchItem(true);

		final List<IFeedback> entities = feedbackService.find(filter);
		List<FeedbackDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("feedback.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new FeedbackDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("feedback.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final FeedbackDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("feedback.edit", hashMap);
		} else {
			final IFeedback entity = fromDtoConverter.apply(formModel);
			feedbackService.save(entity);
			return "redirect:/feedback";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		feedbackService.delete(id);
		return "redirect:/feedback";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IFeedback dbModel = feedbackService.getFullInfo(id);
		final FeedbackDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("feedback.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final FeedbackDTO dto = toDtoConverter.apply(feedbackService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("feedback.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccounts = userAccountService.getAll();
		final List<IItem> items = itemService.getAll();

		final Map<Integer, String> userAccountsMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountsChoices", userAccountsMap);

		final Map<Integer, String> itemsMap = items.stream().collect(Collectors.toMap(IItem::getId, IItem::getName));
		hashMap.put("itemsChoices", itemsMap);
	}
}

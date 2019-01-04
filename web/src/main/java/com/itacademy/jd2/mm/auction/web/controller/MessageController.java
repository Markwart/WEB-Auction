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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;
import com.itacademy.jd2.mm.auction.service.IItemService;
import com.itacademy.jd2.mm.auction.service.IMessageService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.web.converter.MessageFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.MessageToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.MessageDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends AbstractController {

	private IMessageService messageService;
	private IUserAccountService userAccountService;
	private IItemService itemService;

	private MessageToDTOConverter toDtoConverter;
	private MessageFromDTOConverter fromDtoConverter;

	@Autowired
	public MessageController(IMessageService messageService, IUserAccountService userAccountService,
			IItemService itemService, MessageToDTOConverter toDtoConverter, MessageFromDTOConverter fromDtoConverter) {
		super();
		this.messageService = messageService;
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

		final MessageFilter filter = new MessageFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(messageService.getCount(filter));

		filter.setFetchUserAccountFrom(true);
		filter.setFetchUserAccountWhom(true);
		filter.setFetchItem(true);

		final List<IMessage> entities = messageService.find(filter);
		List<MessageDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("message.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new MessageDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("message.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final MessageDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("message.edit", hashMap);
		} else {
			final IMessage entity = fromDtoConverter.apply(formModel);
			messageService.save(entity);
			return "redirect:/message";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		messageService.delete(id);
		return "redirect:/message";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IMessage dbModel = messageService.getFullInfo(id);
		final MessageDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("message.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final MessageDTO dto = toDtoConverter.apply(messageService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("message.edit", hashMap);
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

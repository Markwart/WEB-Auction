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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;
import com.itacademy.jd2.mm.auction.service.IAuctionRuleService;
import com.itacademy.jd2.mm.auction.web.converter.AuctionRuleFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.AuctionRuleToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.AuctionRuleDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/auctionRule")
public class AuctionRuleController extends AbstractController {

	private IAuctionRuleService auctionRuleService;

	private AuctionRuleToDTOConverter toDtoConverter;
	private AuctionRuleFromDTOConverter fromDtoConverter;

	@Autowired
	public AuctionRuleController(IAuctionRuleService auctionRuleService,
			AuctionRuleToDTOConverter toDtoConverter, AuctionRuleFromDTOConverter fromDtoConverter) {
		super();
		this.auctionRuleService = auctionRuleService;
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

		final AuctionRuleFilter filter = new AuctionRuleFilter();
		prepareFilter(gridState, filter);

		final List<IAuctionRule> entities = auctionRuleService.find(filter);
		List<AuctionRuleDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(auctionRuleService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("auctionRule.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IAuctionRule newEntity = auctionRuleService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("auctionRule.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final AuctionRuleDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "auctionRule.edit";
		} else {
			final IAuctionRule entity = fromDtoConverter.apply(formModel);
			auctionRuleService.save(entity);
			return "redirect:/auctionRule"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		auctionRuleService.delete(id);
		return "redirect:/auctionRule";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IAuctionRule dbModel = auctionRuleService.get(id);
		final AuctionRuleDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("auctionRule.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AuctionRuleDTO dto = toDtoConverter.apply(auctionRuleService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("auctionRule.edit", hashMap);
	}
}

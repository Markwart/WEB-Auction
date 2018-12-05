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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;
import com.itacademy.jd2.mm.auction.service.IAuctionDurationService;
import com.itacademy.jd2.mm.auction.web.converter.AuctionDurationFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.AuctionDurationToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.AuctionDurationDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/auctionDuration")
public class AuctionDurationController extends AbstractController {

	private IAuctionDurationService auctionDurationService;

	private AuctionDurationToDTOConverter toDtoConverter;
	private AuctionDurationFromDTOConverter fromDtoConverter;

	@Autowired
	private AuctionDurationController(IAuctionDurationService auctionDurationService,
			AuctionDurationToDTOConverter toDtoConverter, AuctionDurationFromDTOConverter fromDtoConverter) {
		super();
		this.auctionDurationService = auctionDurationService;
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

		final AuctionDurationFilter filter = new AuctionDurationFilter();
		prepareFilter(gridState, filter);

		final List<IAuctionDuration> entities = auctionDurationService.find(filter);
		List<AuctionDurationDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(auctionDurationService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("auctionDuration.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IAuctionDuration newEntity = auctionDurationService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("auctionDuration.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final AuctionDurationDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "auctionDuration.edit";
		} else {
			final IAuctionDuration entity = fromDtoConverter.apply(formModel);
			auctionDurationService.save(entity);
			return "redirect:/auctionDuration"; // generates 302 response with Location="/carsdealer/brand"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		auctionDurationService.delete(id);
		return "redirect:/auctionDuration";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IAuctionDuration dbModel = auctionDurationService.get(id);
		final AuctionDurationDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("auctionDuration.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AuctionDurationDTO dto = toDtoConverter.apply(auctionDurationService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("auctionDuration.edit", hashMap);
	}
}

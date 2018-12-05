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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.web.converter.CountryOriginFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.CountryOriginToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.CountryOriginDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/countryOrigin")
public class CountryOriginController extends AbstractController {

	private ICountryOriginService countryOriginService;

	private CountryOriginToDTOConverter toDtoConverter;
	private CountryOriginFromDTOConverter fromDtoConverter;

	@Autowired
	public CountryOriginController(ICountryOriginService countryOriginService,
			CountryOriginToDTOConverter toDtoConverter, CountryOriginFromDTOConverter fromDtoConverter) {
		super();
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

		final CountryOriginFilter filter = new CountryOriginFilter();
		prepareFilter(gridState, filter);

		final List<ICountryOrigin> entities = countryOriginService.find(filter);
		List<CountryOriginDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(countryOriginService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("countryOrigin.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICountryOrigin newEntity = countryOriginService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("countryOrigin.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CountryOriginDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "countryOrigin.edit";
		} else {
			final ICountryOrigin entity = fromDtoConverter.apply(formModel);
			countryOriginService.save(entity);
			return "redirect:/countryOrigin"; // generates 302 response with Location="/carsdealer/brand"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		countryOriginService.delete(id);
		return "redirect:/countryOrigin";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICountryOrigin dbModel = countryOriginService.get(id);
		final CountryOriginDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("countryOrigin.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CountryOriginDTO dto = toDtoConverter.apply(countryOriginService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("countryOrigin.edit", hashMap);
	}
}

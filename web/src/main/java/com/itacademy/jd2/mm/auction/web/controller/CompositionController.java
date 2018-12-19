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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.web.converter.CompositionFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.CompositionToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.CompositionDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/composition")
public class CompositionController extends AbstractController {

	private ICompositionService compositionService;

	private CompositionToDTOConverter toDtoConverter;
	private CompositionFromDTOConverter fromDtoConverter;

	@Autowired
	public CompositionController(ICompositionService compositionService, CompositionToDTOConverter toDtoConverter,
			CompositionFromDTOConverter fromDtoConverter) {
		super();
		this.compositionService = compositionService;
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

		final CompositionFilter filter = new CompositionFilter();
		prepareFilter(gridState, filter);

		final List<IComposition> entities = compositionService.find(filter);
		List<CompositionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(compositionService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("composition.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IComposition newEntity = compositionService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("composition.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CompositionDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "composition.edit";
		} else {
			final IComposition entity = fromDtoConverter.apply(formModel);
			compositionService.save(entity);
			return "redirect:/composition"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		compositionService.delete(id);
		return "redirect:/composition";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IComposition dbModel = compositionService.get(id);
		final CompositionDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("composition.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CompositionDTO dto = toDtoConverter.apply(compositionService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("composition.edit", hashMap);
	}
}

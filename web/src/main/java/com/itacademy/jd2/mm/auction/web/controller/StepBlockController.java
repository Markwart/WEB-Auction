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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;
import com.itacademy.jd2.mm.auction.service.IStepBlockService;
import com.itacademy.jd2.mm.auction.web.converter.StepBlockFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.StepBlockToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.StepBlockDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/stepBlock")
public class StepBlockController extends AbstractController {

	private IStepBlockService stepBlockService;

	private StepBlockToDTOConverter toDtoConverter;
	private StepBlockFromDTOConverter fromDtoConverter;

	@Autowired
	public StepBlockController(IStepBlockService stepBlockService,
			StepBlockToDTOConverter toDtoConverter, StepBlockFromDTOConverter fromDtoConverter) {
		super();
		this.stepBlockService = stepBlockService;
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

		final StepBlockFilter filter = new StepBlockFilter();
		prepareFilter(gridState, filter);

		final List<IStepBlock> entities = stepBlockService.find(filter);
		List<StepBlockDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(stepBlockService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("stepBlock.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IStepBlock newEntity = stepBlockService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("stepBlock.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final StepBlockDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "stepBlock.edit";
		} else {
			final IStepBlock entity = fromDtoConverter.apply(formModel);
			stepBlockService.save(entity);
			return "redirect:/stepBlock"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		stepBlockService.delete(id);
		return "redirect:/stepBlock";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IStepBlock dbModel = stepBlockService.get(id);
		final StepBlockDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("stepBlock.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final StepBlockDTO dto = toDtoConverter.apply(stepBlockService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("stepBlock.edit", hashMap);
	}
}

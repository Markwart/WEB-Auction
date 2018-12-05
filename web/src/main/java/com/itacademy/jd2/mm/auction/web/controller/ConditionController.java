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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.web.converter.ConditionFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.ConditionToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.ConditionDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/condition")
public class ConditionController extends AbstractController {

	private IConditionService conditionService;

	private ConditionToDTOConverter toDtoConverter;
	private ConditionFromDTOConverter fromDtoConverter;

	@Autowired
	public ConditionController(IConditionService conditionService, ConditionToDTOConverter toDtoConverter,
			ConditionFromDTOConverter fromDtoConverter) {
		super();
		this.conditionService = conditionService;
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

	        final ConditionFilter filter = new ConditionFilter();
	        prepareFilter(gridState, filter);

		final List<ICondition> entities = conditionService.find(filter);
		List<ConditionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(conditionService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("condition.list", models);
	}

	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public ModelAndView showForm() {
	        final Map<String, Object> hashMap = new HashMap<>();
	        final ICondition newEntity = conditionService.createEntity();
	        hashMap.put("formModel", toDtoConverter.apply(newEntity));

	        return new ModelAndView("condition.edit", hashMap);
	    }

	    @RequestMapping(method = RequestMethod.POST)
	    public String save(@Valid @ModelAttribute("formModel") final ConditionDTO formModel, final BindingResult result) {
	        if (result.hasErrors()) {
	            return "condition.edit";
	        } else {
	            final ICondition entity = fromDtoConverter.apply(formModel);
	            conditionService.save(entity);
	            return "redirect:/condition"; // generates 302 response with Location="/carsdealer/brand"
	        }
	    }

	    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
	        conditionService.delete(id);
	        return "redirect:/condition";
	    }

	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
	        final ICondition dbModel = conditionService.get(id);
	        final ConditionDTO dto = toDtoConverter.apply(dbModel);
	        final Map<String, Object> hashMap = new HashMap<>();
	        hashMap.put("formModel", dto);
	        hashMap.put("readonly", true);

	        return new ModelAndView("condition.edit", hashMap);
	    }

	    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
	        final ConditionDTO dto = toDtoConverter.apply(conditionService.get(id));

	        final Map<String, Object> hashMap = new HashMap<>();
	        hashMap.put("formModel", dto);

	        return new ModelAndView("condition.edit", hashMap);
	    }
}

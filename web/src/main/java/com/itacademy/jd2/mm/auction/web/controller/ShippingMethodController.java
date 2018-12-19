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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;
import com.itacademy.jd2.mm.auction.service.IShippingMethodService;
import com.itacademy.jd2.mm.auction.web.converter.ShippingMethodFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.ShippingMethodToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.ShippingMethodDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/shippingMethod")
public class ShippingMethodController extends AbstractController {

	private IShippingMethodService shippingMethodService;

	private ShippingMethodToDTOConverter toDtoConverter;
	private ShippingMethodFromDTOConverter fromDtoConverter;

	@Autowired
	public ShippingMethodController(IShippingMethodService shippingMethodService,
			ShippingMethodToDTOConverter toDtoConverter, ShippingMethodFromDTOConverter fromDtoConverter) {
		super();
		this.shippingMethodService = shippingMethodService;
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

		final ShippingMethodFilter filter = new ShippingMethodFilter();
		prepareFilter(gridState, filter);

		final List<IShippingMethod> entities = shippingMethodService.find(filter);
		List<ShippingMethodDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(shippingMethodService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("shippingMethod.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IShippingMethod newEntity = shippingMethodService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("shippingMethod.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final ShippingMethodDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "shippingMethod.edit";
		} else {
			final IShippingMethod entity = fromDtoConverter.apply(formModel);
			shippingMethodService.save(entity);
			return "redirect:/shippingMethod"; 
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		shippingMethodService.delete(id);
		return "redirect:/shippingMethod";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IShippingMethod dbModel = shippingMethodService.get(id);
		final ShippingMethodDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("shippingMethod.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ShippingMethodDTO dto = toDtoConverter.apply(shippingMethodService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("shippingMethod.edit", hashMap);
	}
}

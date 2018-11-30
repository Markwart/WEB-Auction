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

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.PaymentMethodFilter;
import com.itacademy.jd2.mm.auction.service.IPaymentMethodService;
import com.itacademy.jd2.mm.auction.web.converter.PaymentMethodFromDTOConverter;
import com.itacademy.jd2.mm.auction.web.converter.PaymentMethodToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.PaymentMethodDTO;
import com.itacademy.jd2.mm.auction.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/paymentMethod")
public class PaymentMethodController extends AbstractController {

	private IPaymentMethodService paymentMethodService;

	private PaymentMethodToDTOConverter toDtoConverter;
	private PaymentMethodFromDTOConverter fromDtoConverter;

	@Autowired
	private PaymentMethodController(IPaymentMethodService paymentMethodService,
			PaymentMethodToDTOConverter toDtoConverter, PaymentMethodFromDTOConverter fromDtoConverter) {
		super();
		this.paymentMethodService = paymentMethodService;
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

		final PaymentMethodFilter filter = new PaymentMethodFilter();
		prepareFilter(gridState, filter);

		final List<IPaymentMethod> entities = paymentMethodService.find(filter);
		List<PaymentMethodDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(paymentMethodService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("paymentMethod.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPaymentMethod newEntity = paymentMethodService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("paymentMethod.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PaymentMethodDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "paymentMethod.edit";
		} else {
			final IPaymentMethod entity = fromDtoConverter.apply(formModel);
			paymentMethodService.save(entity);
			return "redirect:/paymentMethod"; // generates 302 response with Location="/carsdealer/brand"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		paymentMethodService.delete(id);
		return "redirect:/paymentMethod";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPaymentMethod dbModel = paymentMethodService.get(id);
		final PaymentMethodDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("paymentMethod.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PaymentMethodDTO dto = toDtoConverter.apply(paymentMethodService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("paymentMethod.edit", hashMap);
	}
}

package com.itacademy.jd2.mm.auction.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.web.converter.ConditionToDTOConverter;
import com.itacademy.jd2.mm.auction.web.dto.ConditionDTO;

@Controller
@RequestMapping(value = "/condition")
public class ConditionController  {

	private IConditionService conditionService;

	private ConditionToDTOConverter toDtoConverter;

	@Autowired
	private ConditionController(IConditionService conditionService, ConditionToDTOConverter toDtoConverter) {
		super();
		this.conditionService = conditionService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final ConditionFilter filter = new ConditionFilter();

		final List<ICondition> entities = conditionService.find(filter);
		List<ConditionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("condition.list", models);
	}

}

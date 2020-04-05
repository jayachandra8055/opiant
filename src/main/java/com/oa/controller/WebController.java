package com.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.model.MathExpression;
import com.oa.service.ExpressionService;

@Controller
public class WebController {

	@Autowired
	ExpressionService service;

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/addExpression")
	public ModelAndView addExpression(MathExpression expression) {
		expression = service.createOrUpdateEmployee(expression);
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", expression);
		mv.setViewName("result");
		return mv;
	}
}
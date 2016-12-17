package com.userndot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "helloWorld";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView page() {
		ModelAndView mav = new ModelAndView("@dashboard");
		Map<String, Object> modelMap = new ModelMap();
		mav.addAllObjects(modelMap );
		return mav;
	}
}

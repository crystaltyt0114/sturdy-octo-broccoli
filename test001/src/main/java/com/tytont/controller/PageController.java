package com.tytont.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("name", "zhangsan");
		return "index";
	}

	@RequestMapping("t1")
	public ModelAndView t1(String name) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject(name, name.toString());
		return modelAndView;
	}

}

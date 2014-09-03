package com.list.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author : Mukul.Sharma
 */

@Controller
public class ListController {

	@RequestMapping(value = "/home")
	public String greet(Model model) {
		model.addAttribute("greeting", "Listifying begins !");
		return "hello";
	}

	// @RequestMapping(value = "/")
	// @ResponseBody
	// public String greeting(Model model) {
	// return "<h1> Welcome !";
	// }
}

package com.list.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.list.app.model.User;
import com.list.app.service.UserService;

/**
 * Author : Mukul.Sharma
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listUsers(Model model) {
		return userService.getAll();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String seeUser(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String inserData(@ModelAttribute User user) {
		System.out.println("Adding user:");
		if (user != null)
			userService.addUser(user);
		return "redirect:list";
	}

}

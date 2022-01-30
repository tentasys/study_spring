package com.pjt.pjt16;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	//전부 GET방식 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		System.out.println("home method");
		model.addAttribute("key", "value");

		return "home2";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {

		System.out.println("login method");
		model.addAttribute("key", "value2");
		return "login";
	}
	
}

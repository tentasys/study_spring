package com.pjt.pjt14;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//컨트롤러의 역할을 하는 어노테이션 
@Controller
public class Login {

	//이러한 요청이 들어왔을 때 실행하게 해라 
	//GET은 명시하지 않고 생략해도 된다.
	@RequestMapping(value="/login", method=RequestMethod.GET)
//	@RequestMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("loginKey", "loginValue");
		
		return "login";	//login.jsp
	}
	
}

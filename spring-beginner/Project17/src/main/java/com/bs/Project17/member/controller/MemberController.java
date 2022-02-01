package com.bs.Project17.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.Project17.member.Member;
import com.bs.Project17.member.service.MemberService;

//요청이 오면 handlermapping bean이 컨트롤러를 찾는다.
//이후 handleradaptor가 memJoin에 맞는 메소드를 찾는다.
@Controller
@RequestMapping("/member") //공통된 부분에 대한 처리를 할 수 있음 
public class MemberController {

	//방법 1 : 순수 자바를 사용한 방법 -> 스프링의 장점을 사용하지 않음(스프링의 장점 : 스프링 컨테이너에 빈 객체를 올려서 사용 할 수 있다.)
	//MemberService service = new MemberService();
	//방법 2 : 빈 객체 생성 후 Autowired를 통한 자동주입 
	@Autowired
	MemberService service;
	
	//POST로 Request를 했는데 controller에 post method가 없을 경우 -> 찾다가 없으면 동명의 GET Method를 찾는다.
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)	//RequestMethod는 기본값이 GET이므로 명시할 필요가 없다.
//	public String memJoin(Model model, HttpServletRequest request) {
	//public String memJoin(Member member) {		//사용자가 정보를 날리면 setter가 작동하여 사용자가 입력한 값이 객체의 프로퍼티로 들어간다.
	public String memJoin(@ModelAttribute("mem") Member member) {			//뷰단에서 다른 닉네임으로 커맨드 객체를 사용하고자 할 때 -> java단에서는 그대로 Member를 사용하지만 view단에서는 닉네임 사용 
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		String memMail = request.getParameter("memMail");
//		String memPhone1 = request.getParameter("memPhone1");
//		String memPhone2 = request.getParameter("memPhone2");
//		String memPhone3 = request.getParameter("memPhone3");
		
//		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
		
		//아래의 코드 : 커맨드 객체 사용 
		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());
		
//		model.addAttribute("memId", memId);
//		model.addAttribute("memPw", memPw);
//		model.addAttribute("memMail", memMail);
//		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		return "memJoinOK";
	}
	
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
//	1. HttpServletRequest request를 이용 
//	2. annotaion을 이용 
//	public String memLogin(Model model, HttpServletRequest request) {
	//requried : 꼭 있어야 하는 값인지 아닌지 판단. false의 경우 exception이 발생하지 않는다.
	//defaultvalue : 값이 넘어오지 않을 때를 대비한 속성값 
	public String memLogin(Model model, @RequestParam("memID") String memId, @RequestParam(value="memID", required=false, defaultValue="123") String memPw) {
		
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
		
		Member member = service.memberSearch(memId, memPw);
		
		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "memLoginOK";
	}
	
	@RequestMapping(value="/memRemove", method = RequestMethod.POST)
	public String memRemove(@ModelAttribute("mem") Member member) {
		
		service.memberRemove(member);
		
		return "memRemoveOK";
	}
	
	@RequestMapping(value="/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member) {
		
		Member[] members = service.memberModify(member);
		
		ModelAndView mav = new ModelAndView();
		
		//데이터 추가 
		mav.addObject("memBef", members[0]);
		mav.addObject("memAft", members[1]);
		
		//뷰의 이름 추가 
		mav.setViewName("memModifyOK");
		
		return mav;
	}
	
	//메소드 위에 modelattribute 를 주고 나면 view단에서 메소드를 바로 사용할 수 있다. 
	//어떠한 url로 호출되던지간에 modelattribute로 준 값이 공통적으로 실행이 된다. 
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
}
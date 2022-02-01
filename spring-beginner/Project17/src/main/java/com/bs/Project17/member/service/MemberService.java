package com.bs.Project17.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bs.Project17.member.Member;
import com.bs.Project17.member.dao.MemberDao;

//컨트롤러로 쓰이면 controller annotaion을 붙이는것 과 같이 어노테이션을 붙여서 사용 가능 
//서비스 객체로 스프링 컨테이너에 담기면 된다.
//자동으로 스프링 컨테이너에 담긴다 -> 사용하는곳에서 Autowired를 써우면 사용 가능하다.
//아래 세 어노테이션 중 하나를 사용 
//@Component
//@Repository
@Service
public class MemberService implements IMemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public void memberRegister(String memId, String memPw, String memMail,
			String memPhone1, String memPhone2, String memPhone3) {
		System.out.println("memberRegister()");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);
		System.out.println("memMail : " + memMail);
		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
	}

	@Override
	public Member memberSearch(String memId, String memPw) {
		System.out.println("memberSearch()");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);
		
		Member member = dao.memberSelect(memId, memPw);
		
		return member;
	}

	@Override
	public Member[] memberModify(Member member) {
		
		Member[] ret = new Member[2];
		ret[0] = member;
		ret[1] = member;
		
		return ret;
	}

	@Override
	public void memberRemove(Member member) {
		dao.memberDelete(member.getMemId());
	}

}
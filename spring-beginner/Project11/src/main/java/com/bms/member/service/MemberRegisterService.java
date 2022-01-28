package com.bms.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bms.member.Member;
import com.bms.member.dao.MemberDao;

public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService() {}
	
	public void register(Member member) {
		memberDao.insert(member);
	}
	
	public void initMethod() {
		System.out.println(" -- initMethod() -- ");
	}
	
	public void destroyMethod() {
		System.out.println(" -- destroyMethod() -- ");
	}
}

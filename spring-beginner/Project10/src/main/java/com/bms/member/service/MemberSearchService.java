package com.bms.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bms.member.Member;
import com.bms.member.dao.MemberDao;

public class MemberSearchService {
	@Autowired
	private MemberDao memberDao;
	
	public MemberSearchService() {}
	
	public Member searchMember(String mId) {
		return memberDao.select(mId);
	}
	
}
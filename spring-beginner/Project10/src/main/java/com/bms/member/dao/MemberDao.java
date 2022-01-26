package com.bms.member.dao;

import java.util.HashMap;
import java.util.Map;
import com.bms.member.*;

public class MemberDao {
	private Map<String, Member> memberDB = new HashMap<String, Member>();
	
	public void insert(Member member) {
		memberDB.put(member.getmId(), member);
	}
	
	public Member select(String mid) {
		return memberDB.get(mid);
	}
	
	public void update(Member member) {
		
	}
	
	public void delete(String mId) {
		
	}
	
	public Map<String, Member> getMemberDB(){
		return memberDB;
	}
}

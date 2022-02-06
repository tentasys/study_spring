package com.bs.lec21.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bs.lec21.member.Member;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//데이터베이스가 있는 경로 
	private String userid = "scott";
	private String userpw = "tiger";
	
	//커넥션에 필요한 변수 
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		//dbMap = new HashMap<String, Member>();
	}
	

//	public Map<String, Member> memberInsert(Member member) {
//		
//		dbMap.put(member.getMemId(), member);
//		return dbMap;
//		
//	}
	@Override
	public int memberInsert(Member member) {
		
		int result = 0;
		
		//데이터베이스 및 네트워크와 연결되는 것이므로 예외처리를 해 주어야 한다. 
		try {
			Class.forName(driver);	//오라클 jdbc 드라이버를 메모리에 로딩 
			conn = DriverManager.getConnection(url, userid, userpw);	//연결 객체를 가져옴(url)
			String sql = "INSERT INTO member (memId, memPW, memMail) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemMail());
			
			//데이터베이스에 쿼리 질의 
			result = pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//자원 해제 부분 
			try {
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		
//		Member mem = dbMap.get(member.getMemId());
//		return mem;
		
		Member mem = null;
		
		try {
			Class.forName(driver);	//오라클 jdbc 드라이버를 메모리에 로딩 
			conn = DriverManager.getConnection(url, userid, userpw);	//연결 객체를 가져옴(url)
			String sql = "SELECT * FROM member WHERE memID = ? AND memPW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			
			//데이터베이스에 쿼리 질의 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("memId");
				String memPw = rs.getString("memPw");
				String memMail = rs.getString("memMail");
				int memPurcNum = rs.getInt("memPurcNum");
				
				mem = new Member();
				mem.setMemId(memId);
				mem.setMemPw(memPw);
				mem.setMemMail(memMail);
				mem.setMemPurcNum(memPurcNum);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//자원 해제 부분 
			try {
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return mem;
	}

	@Override
	public int memberUpdate(Member member) {
		
//		dbMap.put(member.getMemId(), member);
//		return dbMap.get(member.getMemId());
		int result = 0;
		
		//데이터베이스 및 네트워크와 연결되는 것이므로 예외처리를 해 주어야 한다. 
		try {
			Class.forName(driver);	//오라클 jdbc 드라이버를 메모리에 로딩 
			conn = DriverManager.getConnection(url, userid, userpw);	//연결 객체를 가져옴(url)
			String sql = "UPDATE member SET memPw = ?, memMail = ? where memID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setString(3, member.getMemId());
			
			//데이터베이스에 쿼리 질의 
			result = pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//자원 해제 부분 
			try {
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int memberDelete(Member member) {
		
//		dbMap.remove(member.getMemId());
//		return dbMap;
		
		int result = 0;
		
		//데이터베이스 및 네트워크와 연결되는 것이므로 예외처리를 해 주어야 한다. 
		try {
			Class.forName(driver);	//오라클 jdbc 드라이버를 메모리에 로딩 
			conn = DriverManager.getConnection(url, userid, userpw);	//연결 객체를 가져옴(url)
			String sql = "DELETE member where memID = ? and memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			
			//데이터베이스에 쿼리 질의 
			result = pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//자원 해제 부분 
			try {
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

}

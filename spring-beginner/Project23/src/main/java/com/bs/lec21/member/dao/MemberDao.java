package com.bs.lec21.member.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bs.lec21.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;
//import com.mchange.v1.db.sql.DriverManagerDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//데이터베이스가 있는 경로 
	private String userid = "scott";
	private String userpw = "tiger";
	
	//pom.xml에서 dependency를 추가했기에 사용 가능. 
	//private DriverManagerDataSource dataSource;
	//private DriverManagerDataSource dataSource;	//데이터베이스와 관련된 정보들을 가지고 있는 객체 
	private ComboPooledDataSource dataSource;
	private JdbcTemplate template;
	
	//커넥션에 필요한 변수 -> Jdbc Template로 묶는다 
/*
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
*/

	//DAO객체가 생성될 때 딱 한 번 세팅되고 넘어간다. 
	//스프링 컨테이너의 빈 객체에 connection pool을 설정하지 않았으면 아래와 같이 설정한다.
	public MemberDao () {
		dataSource = new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
		}catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	//스프링 설정 파일에 connection pool을 설정하면 아래와 같이 사용 가능 
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	private HashMap<String, Member> dbMap;
	
	//public MemberDao() {
		//dbMap = new HashMap<String, Member>();
	//}
	

//	public Map<String, Member> memberInsert(Member member) {
//		
//		dbMap.put(member.getMemId(), member);
//		return dbMap;
//		
//	}
	@Override
	public int memberInsert(Member member) {
		
		int result = 0;
		
		String sql = "INSERT INTO member (memId, memPW, memMail) values (?, ?, ?)";
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());
		
		//데이터베이스 및 네트워크와 연결되는 것이므로 예외처리를 해 주어야 한다. 
		// jdbc template을 사용한다면 아래 소스는 사용하지 않아도 됨. 
		/*
		try {
			Class.forName(driver);	//오라클 jdbc 드라이버를 메모리에 로딩 
			conn = DriverManager.getConnection(url, userid, userpw);	//연결 객체를 가져옴(url)
			
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
		}*/
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		
//		Member mem = dbMap.get(member.getMemId());
//		return mem;
		
		List<Member> members = null;
		final String sql = "SELECT * FROM member WHERE memID = ? AND memPW = ?";	//값이 변동되지 않도록 상수화
		
		//preparedstatementsetter, preparedstatement creator 등 어느 방법을 이용해도 된다. 
		members = template.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
			
		}, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				
				return mem;
			}
			
		});
		
		if(members.isEmpty())	return null;
		return members.get(0);
		/*
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
		
		return mem;*/
	}

	@Override
	public int memberUpdate(Member member) {
		
//		dbMap.put(member.getMemId(), member);
//		return dbMap.get(member.getMemId());
		int result = 0;
		
		final String sql = "UPDATE member SET memPw = ?, memMail = ? where memID = ?";
		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemId());
		
		//데이터베이스 및 네트워크와 연결되는 것이므로 예외처리를 해 주어야 한다. 
		/*
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
		*/
		return result;
	}

	@Override
	public int memberDelete(Member member) {
		
//		dbMap.remove(member.getMemId());
//		return dbMap;
		
		int result = 0;
		final String sql = "DELETE member where memID = ? and memPw = ?";
		result = template.update(sql, member.getMemId(), member.getMemPw());
		
		/*
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
		}*/
		
		return result;
		
	}

}

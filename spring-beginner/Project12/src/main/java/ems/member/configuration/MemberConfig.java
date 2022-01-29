package ems.member.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;
import ems.member.dao.StudentDao;
import ems.member.service.EMSInformationService;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentDeleteService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

//applicationContext.xml을 대체한다 

//스프링 설정파일로서 컨테이너를 생성할 수 있도록 하는 어노테이션 
@Configuration
public class MemberConfig {

//	<bean id="studentDao" class="ems.member.dao.StudentDao" ></bean>
	//메소드 이름 : bean 태그의 id값 
	//반환형 : 객체의 데이터 타입 
	//어노테이션도 포함해야 함 
	@Bean
	public StudentDao studentDao() {
		return new StudentDao();	//새로운 객체를 반환 
	}
	
//	<bean id="registerService" class="ems.member.service.StudentRegisterService">
//	<constructor-arg ref="studentDao" ></constructor-arg>
//</bean>
	
	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}
	
	@Bean
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}
	
	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}
	
	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}
	
	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService(studentDao());
	}
	
//	<bean id="dataBaseConnectionInfoDev" class="ems.member.DataBaseConnectionInfo">
//	<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
//	<property name="userId" value="scott" />
//	<property name="userPw" value="tiger" />
//</bean>
	//프로퍼티로 존재하는 것은 객체를 만들고 그 객체에 맞게 설정해야 함.
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
		infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoDev.setUserId("scott");
		infoDev.setUserPw("tiger");
		
		return infoDev;
	}
	
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
		infoReal.setJdbcUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		infoReal.setUserId("masterid");
		infoReal.setUserPw("masterpw");
		
		return infoReal;
	}
	
	
	@Bean
	public EMSInformationService informationService() {
		EMSInformationService info = new EMSInformationService();
		
		info.setInfo("Education Management System program was developed in 2015.");
		info.setCopyRight("COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		info.setVer("The version is 1.0");
		info.setsYear(2015);
		info.setsMonth(1);
		info.setsDay(1);
		info.seteYear(2015);
		info.seteMonth(2);
		info.seteDay(28);
		
//		<property name="developers">
//		<list>
//			<value>Cheney.</value>
//			<value>Eloy.</value>
//			<value>Jasper.</value>
//			<value>Dillon.</value>
//			<value>Kian.</value>
//		</list>
//	</property>
		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);
		
		
//		<property name="administrators">
//		<map>
//			<entry>
//				<key>
//					<value>Cheney</value>
//				</key>
//				<value>cheney@springPjt.org</value>
//			</entry>
//			<entry>
//				<key>
//					<value>Jasper</value>
//				</key>
//				<value>jasper@springPjt.org</value>
//			</entry>
//		</map>
//	</property>
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);
		
//		<property name="dbInfos">
//		<map>
//			<entry>
//				<key>
//					<value>dev</value>
//				</key>
//				<ref bean="dataBaseConnectionInfoDev"/>
//			</entry>
//			<entry>
//				<key>
//					<value>real</value>
//				</key>
//				<ref bean="dataBaseConnectionInfoReal"/>
//			</entry>
//		</map>
//	</property>		
		
		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		dbInfos.put("dev", dataBaseConnectionInfoDev());
		dbInfos.put("real", dataBaseConnectionInfoReal());
		info.setDbInfos(dbInfos);
		
		return info;
	}
}

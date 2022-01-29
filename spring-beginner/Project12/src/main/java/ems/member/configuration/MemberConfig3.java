package ems.member.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;
import ems.member.service.EMSInformationService;

@Configuration
public class MemberConfig3 {
	
	//MemberConfig2에서 만든 빈도 같은 컨테이너에 생성되므로, 여기서 autowired를 해도 자동 주입이 된다. 
	//컨테이너 하나에 모든 빈 객체가 생성되므로, 다른 파일에서 생성된 빈이 연결이 된다. 
	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoDev;
	
	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoReal;

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
		
		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);
		
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);
		
		
		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		//참조의 문제가 생김 
		dbInfos.put("dev", dataBaseConnectionInfoDev);
		dbInfos.put("real", dataBaseConnectionInfoReal);
		info.setDbInfos(dbInfos);
		
		return info;
	}
}

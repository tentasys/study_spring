package ems.member.main;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

import ems.member.Student;
import ems.member.service.EMSInformationService;
//import ems.member.assembler.StudentAssembler;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

public class MainClass {

	public static void main(String[] args) {
		String[] sNums = {"S11111111", "B22222222", "C33333333",
						  "D44444444", "G55555555", "T66666666",
						  "H77777777", "U88888888", "Q99999999",
						  "Z00000000"};
		
		String[] sIds = {"rabbit", "hippo", "raccon", "elephant", "lion",
						 "tiger", "pig", "horse", "bird", "deer"};
		
		String[] sPws = {"11111", "22222", "33333", "44444", "55555",
						 "66666", "77777", "88888", "99999", "00000"};
		
		String[] sNames = {"agatha", "babara", "chris", "doris", "elva",
						   "fiona", "holly", "jasmin", "lena", "melissa"};
		
		int[] sAges = {19, 22, 20, 27, 19, 21, 19, 25, 22, 24};
		String[] sGenders = {"M", "F", "F", "M", "M", "M", "F", "M", "F", "F"};
		String[] sMajors = {"English Literature", "Korean Language and Literature",
				"French Language and Literature", "Philosophy", "History",
				"Law", "Statistics", "Computer Engineering", "Economics",
				"Public Administration"};
		
		//학생 정보를 가지고 학생 한명마다 객체화 시키는 클래스 
		//StudentAssembler assembler = new StudentAssembler();
		//이 한 줄로 모든 객체 생성과 의존 주입이 끝남 
		//컨테이너 생성 getbean을 이용해 원하는 객체를 가져다가 사용함. 
		//분리한 설정 파일을 통해 스프링 컨테이너를 만든다. 
		String appCtxs[] = {"classpath:appCtx1.xml", "classpath:appCtx2.xml", "classpath:appCtx3.xml"};	//배열 형태로 넣으면 여러 개의 파일을 동시에 사용 가능 
		//GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(appCtxs);
		//import한 설정파일 사용 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtxImport.xml");
		
		EMSInformationService informationService = ctx.getBean("informationService", EMSInformationService.class);
		informationService.outputEMSInformation();
		
		//StudentRegisterService regieterService = assembler.getRegisterService();
		StudentRegisterService regieterService = ctx.getBean("registerService", StudentRegisterService.class);
		for(int i=0; i<sNums.length; i++) {
			Student student = new Student(sNums[i], sIds[i], sPws[i], sNames[i],
					sAges[i], sGenders[i], sMajors[i]);
			regieterService.register(student);
		}
		
		StudentModifyService modifyService = ctx.getBean("modifyService", StudentModifyService.class);
		//StudentModifyService modifyService = assembler.getModifyService();
		modifyService.modify(new Student("Z00000000", "deer", "ZZZZZ", "melissa",
				26, "F", "Piano"));
		
		StudentSelectService selectService = ctx.getBean("selectService", StudentSelectService.class);
		//StudentSelectService s electService = assembler.getSelectService();
		Student modifiedStudent = selectService.select("Z00000000");
		System.out.print("sNum:" + modifiedStudent.getsNum() + "\t");
		System.out.print("|sId:" + modifiedStudent.getsId() + "\t");
		System.out.print("|sPw:" + modifiedStudent.getsPw() + "\t");
		System.out.print("|sName:" + modifiedStudent.getsName() + "\t");
		System.out.print("|sAge:" + modifiedStudent.getsAge() + "\t");
		System.out.print("|sGender:" + modifiedStudent.getsGender() + "\t");
		System.out.println("|sMajor:" + modifiedStudent.getsMajor() + "\n");
		
		StudentAllSelectService allSelectService = ctx.getBean("allSelectService", StudentAllSelectService.class);
		//StudentAllSelectService allSelectService = assembler.getAllSelectService();
		Map<String, Student> allStudent = allSelectService.allSelect();
		Set<String> keys = allStudent.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Student student = allStudent.get(key);
			System.out.print("sNum:" + student.getsNum() + "\t");
			System.out.print("|sId:" + student.getsId() + "\t");
			System.out.print("|sPw:" + student.getsPw() + "\t");
			System.out.print("|sName:" + student.getsName() + "\t");
			System.out.print("|sAge:" + student.getsAge() + "\t");
			System.out.print("|sGender:" + student.getsGender() + "\t");
			System.out.println("|sMajor:" + student.getsMajor() + "\n");
		}
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String str = "";
			
			System.out.println("\n==================================================================="
					+ "==============================================================================");
			System.out.println("Select number.");
			System.out.println("1. Check student information");
			System.out.println("2. Exit\n");
			
			str = scanner.next();
			if(str.equals("2")) {
				System.out.println("exit");
				ctx.close();
				scanner.close();
				break;
			}
			else if(str.equals("1")){
				System.out.println("Please input your class number.");
				
				str = scanner.next();
				Student student = selectService.select(str);
				System.out.print("sNum:" + student.getsNum() + "\t");
				System.out.print("|sId:" + student.getsId() + "\t");
				System.out.print("|sPw:" + student.getsPw() + "\t");
				System.out.print("|sName:" + student.getsName() + "\t");
				System.out.print("|sAge:" + student.getsAge() + "\t");
				System.out.print("|sGender:" + student.getsGender() + "\t");
				System.out.println("|sMajor:" + student.getsMajor());
			}
			else {
				System.out.println("Please input correct number.");
			}
		}
	}

}

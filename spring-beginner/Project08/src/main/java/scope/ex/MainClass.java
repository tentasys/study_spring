package scope.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//스프링 설정 파일을 이용한 스프링 컨테이너 생성 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");

		InjectionBean injectionBean = ctx.getBean("injectionBean", InjectionBean.class);
		DependencyBean dependencyBean01 = ctx.getBean("dependencyBean", DependencyBean.class);
		DependencyBean dependencyBean02 = ctx.getBean("dependencyBean", DependencyBean.class);
		
		if(dependencyBean01.equals(dependencyBean02))
			System.out.println("dependencyBean01 == dependencyBean02");	//싱글톤 : 같은 객체를 가리킴 
		else
			System.out.println("dependencyBean01 != dependencyBean02");	//프로토타입 : 다른 객체를 가리킴 
		
		ctx.close();
	}

}
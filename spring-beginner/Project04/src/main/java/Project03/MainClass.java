package Project03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
//		TransportationWalk transportationwalk = new TransportationWalk();
//		transportationwalk.move();
		
		//applicationContext에 의해 만들어진 객체에 접근하기 위해서는 우선 스프링 컨테이너부터 접근해야 함 
		
		//container에 접근하기 위한 데이터 타입 
		//파라미터로 리소스를 적어준다. 이 xml파일을 이용하여 GenericXmlApplicationContext를 만든다. 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		//컨테이너로부터 컨테이너에 있는 객체인 빈을 가져옴. 인자로 빈의 아이디/데이터 타입을 준다. 
		TransportationWalk transportationwalk = ctx.getBean("tWalk", TransportationWalk.class);
		transportationwalk.move();
		
		//사용한 리소스의 반환 
		ctx.close();
	}
}

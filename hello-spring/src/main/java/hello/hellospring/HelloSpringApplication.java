package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		//이 어노테이션이 있는 패키지 안의 파일을 모두 찾아서 스프링 빈에 등록
//이 패키지와 동일하거나 하위패키지가 아닌 것들은 컴포넌트 스캔을 하지 않음.
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

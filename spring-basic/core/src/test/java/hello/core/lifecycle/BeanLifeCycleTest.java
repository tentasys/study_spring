package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //ConfigurableApplicationContext이 AnnotationConfigApplicationContext의 상위 인터페이스이다
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();     //이건 기본 ApplicationContext에서 제공해주지 않는다. AnnotationConfigApplicationContext 혹은 ConfigurableApplicationContext로 받아주자.
        //close는 잘 할 일이 없기 때문에 기본으로 제공해주지 않는다. 하위까지 내려가자.
    }

    @Configuration
    static class LifeCycleConfig {

        // @Bean(initMethod = "init", destroyMethod = "close") //destroyMethod = "(inferred)" 이렇게 잡혀 있다
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;   //호출된 결과물이 spring bean으로 등록이 된다.
        }
    }
}

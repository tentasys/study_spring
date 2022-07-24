package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A : A 사용자가 10000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //Thread B : B 사용자가 20000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //Thread A : 사용자 A가 본인이 주문한 금액을 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    //이 스프링 컨테이너는 빈 하나만 생성해서 사용
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}

package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);//여기 인자를 넣은 대상은 컴포넌트 스캔처럼 동작하기 때문에, 굳이 Component를 써 주지 않아도 된다.
        System.out.println("find prototypeBean1");  //프로토타입 빈을 조회하기 직전에 생성
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);     //이 떼 init 호출
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);      //이 떼 init 호출

        //출력해보면 서로 다른 참조값이 나온다.
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //destroy가 호출되지 않음
        //직접 호출해 줘야 한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

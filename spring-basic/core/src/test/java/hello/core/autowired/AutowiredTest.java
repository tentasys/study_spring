package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        //이렇게 넣어주면 TestBean이 스프링에 등록이 된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    // 임의의 테스트 클래스 생성
    static class TestBean{
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {    //member는 spring이 관리하는 bean이 아니다.
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {    //member는 spring이 관리하는 bean이 아니다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {    //member는 spring이 관리하는 bean이 아니다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//JUnit 5부터는 Public 설정을 하지 않아도 된다.
public class ApplicationContextInfoTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);   //타입을 지정하지 않았기 때문에 Object가 떠야 한다.
            System.out.println("beanDefinitionName = " + beanDefinitionName + " object = "+bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//빈 하나하나에 대한 메타데이터 정보. defenitionname을 넣으면 꺼낼 수 있다.

            //크게 3가지의 롤이 존재. 주로 application만 쓴다.
            //내가 애플리케이션을 개발하기 위해 등록한 빈들(외부 라이브러리) -> 그럼 나머지 롤은?

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);   //타입을 지정하지 않았기 때문에 Object가 떠야 한다.
                System.out.println("beanDefinitionName = " + beanDefinitionName + " object = "+bean);
            }
        }
    }
}

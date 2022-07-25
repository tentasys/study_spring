package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.") //자동 의존관계 주입 시 필요함
    void findBeanByParentTypeDuplicate(){
        try{
            DiscountPolicy bean = ac.getBean(DiscountPolicy.class); //부모 타입으로 조회 시, 아래의 두 자식들이 조회됨
            //NoUniqueBeanDefinitionException 발생
        }catch (NoUniqueBeanDefinitionException e){
            assertThrows(NoUniqueBeanDefinitionException.class,
                    () -> ac.getBean(DiscountPolicy.class));
        }

    }

//    @Test
//    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 자식은 빈 이름으로 지정하면 된다.")
//    void findBeanByParentTypeBeanName(){
//        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
//        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
//    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        //item로 입력하면 자동완성
        //공부할때는 출력 처리를 진행해도 되나, 실제로 테스트케이스를 짤 때는 출력하면 안 됨 - 결론적으로는 자동 통과는 시스템이 결정해야 하는데, 눈으로 확인해서는 안 된다.
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        //이거 돌리면 스프링 내부 빈들까지 다 튀어 나옴 -> 자바 객체는 모든 것이 object타입이기 때문
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        //상위 타입으로 선언하는 이유 : 개발하거나 설계를 할 때 역할과 구현을 쪼개는 것이라고 보면 됨
        //얘는 DiscountPolicy라고 판별
        //다른데서도 의존성 주입하기 편함
        @Bean
        public DiscountPolicy rateDisCountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}

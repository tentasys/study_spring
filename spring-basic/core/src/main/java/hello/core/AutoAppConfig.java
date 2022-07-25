package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//basePackage 관련 옵션이 없으면, ComponentScan이 붙은 클래스가 있는 패키지부터 시작한다. -> hello.core 가 됨
@ComponentScan (  //컴포넌트 스캔을 위한 어노테이션 -> @Component 가 붙은 어노테이션을 찾아서 자동으로 등록
        basePackages = "hello.core.member", //이 위치에서부터 시작해서 찾아들어감 -> 이러면 order 관련된건 등록이 안 됨(여기는 안 간다)
        basePackageClasses = AutoAppConfig.class,   //그러면 hello.core서부터 찾는다.
        //컴포넌트 스캔 중 뺄 것을 지정
        //Configuration annotation이 붙은 건 뺀다. (AppConfig와 충돌을 막기 위함)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // filed Injection 예시
    // ------------------------
//    @Autowired MemberRepository memberRepository;
//    @Autowired
//    DiscountPolicy discountPolicy;
//
//    @Bean
//    OrderService orderService(){
//        return new OrderServiceImpl(discountPolicy, memberRepository);
//    }
    // ------------------------

    //MemoryMemberRepository에 @Component가 붙어 있음 -> 기본이 memoryMemberRepository로 생성됨
    //컴포넌트 스캔과 충돌 테스트하기 위해 생성했던 것 - 이대로 실행하면 BeanDefinitionOverrideException 이 발생한다.
//    @Bean(name="memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}

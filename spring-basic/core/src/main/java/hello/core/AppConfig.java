package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//순수한 자바 코드를 사용한 AppConfig
//public class AppConfig {
//    //이전에는 객체를 생성하고 이 인터페이스에 어떤 것이 들어가야할지를 MemberService 내에서 직접 했었음다
//    //애플리케이션 환경 구성에 대한 것은 AppConfig에서 전부 해 준다.
//    //생성자를 통해서 인스턴스 객체가 들어간다 -> 생성자 주입
//    //멤버 서비스 역할
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    //멤버 리포지토리 역할
//    private MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(discountPolicy(), memberRepository());
//    }
//
//    public DiscountPolicy discountPolicy(){
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//
//    //이렇게 하면 메서드 명을 가져오는 순간 역할이 드러난다.
//}

@Configuration      //애플리케이션의 구성 정보를 담당하는 어노테이션
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    // -> 이러면 singleton이 깨지는것이 아닐까?

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //결과적으로는 memberRepository가 3번 호출이 되어야 함

    //그런데 결과는
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //순서대로 나온다! - 스프링이 어떠한 방법을 쓰더라도 스프링을 보장한다. 어떻게 하는거지..

    @Bean   //각 메서드에 빈이라고 적어 준다. -> 빈이라고 적어주면 스프링 컨테이너에 등록이 된다.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}
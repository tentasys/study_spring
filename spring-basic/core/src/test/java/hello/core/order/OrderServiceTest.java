package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    MemberService memberService;
    OrderService orderService;

    //각 테스트를 실행하기 전 무조건 실행하는 것
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();      //테스트 실행 전 Appconfig 생성
        memberService = appConfig.memberService();  //memberservice에 할당
        orderService = appConfig.orderService();
        //테스트가 두 개 있으면 얘가 두번 돈다.
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    //스프링 컨테이너 필요 없이 순수 자바 코드로 조립하는 것
    //테스트 코드 상에서 필요한 구현체들을 조립해서
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        //OrderServiceImpl 로직 자체를 테스트. 잘 만들어졌는지.
        OrderServiceImpl orderService = new OrderServiceImpl(new FixDiscountPolicy(), new MemoryMemberRepository());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        //수정자 주입을 할 경우 -> 여기서 NullPointerException 발생 -> 가짜 repository라도 넣어주어야 함
        //의존 관계가 뭐가 들어가는지 모른다 - 코드를 까 봐야 안다...

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

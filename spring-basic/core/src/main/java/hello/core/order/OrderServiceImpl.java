package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //회원 찾아야 한다
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //정률할인으로 바꾸려면, 아래 정책을 바꾸면 된다.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //인터페이스에만 의존하도록 아래와 같이 코드 변경
    private final DiscountPolicy discountPolicy;  //구체화가 아닌 추상화에 의존.
    private final MemberRepository memberRepository;    //final은 기본으로 할당되거나 생성자로 반드시 할당되어야 함
    //구체적인 클래스가 아니라 철저한 인터페이스에 의존!

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    //memberid로 member 찾기
        int discountPrice = discountPolicy.discount(member, itemPrice); //orderservice 입장에서는 할인의 동작 방법을 모른다. 설계를 위임함. -> 단일 책임 원칙이 잘 지켜진다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

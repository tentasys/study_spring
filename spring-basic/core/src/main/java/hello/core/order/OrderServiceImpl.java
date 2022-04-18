package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //회원 찾아야 한다
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    //memberid로 member 찾기
        int discountPrice = discountPolicy.discount(member, itemPrice); //orderservice 입장에서는 할인의 동작 방법을 모른다. 설계를 위임함. -> 단일 책임 원칙이 잘 지켜진다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

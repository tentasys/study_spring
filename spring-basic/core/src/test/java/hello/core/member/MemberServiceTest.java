package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given : 이러한 것이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이렇게 된다 -> 넣은 멤버랑 찾은 멤버랑 같은지 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

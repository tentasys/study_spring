package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    //각 테스트를 실행하기 전 무조건 실행하는 것
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();      //테스트 실행 전 Appconfig 생성
        memberService = appConfig.memberService();  //memberservice에 할당
        //테스트가 두 개 있으면 얘가 두번 돈다.
    }

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

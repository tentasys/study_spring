package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//이 테스트는 DB 및 스프링과 연동되지 않은 순수한 자바 코드 -> 단위 테스트
//자바 코드로 하면서 최소한의 단위로 하는 것
//순수한 자바 테스트가 훨씬 좋은 테스트 일 확률이 높다 -> 단위로 쪼개서 테스트
//스프링 컨테이너 없이 테스트를 짤 수 있도록 해야 한다.
class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //-> 그런데 이렇게 하게 되면, MemberService class에서의 finalmemberRepository이랑 겹치게 되는데???
    //-> memberServicedml memberRepository를 직접 생성하는 것이 아닌 contructor를 이용해 외부에서 생성하는 것으로 변경
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach //테스트가 실행되기 전에 각각 동작
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //-> DI, Dependency Injection
    }

    @AfterEach //어떤 메소드가 끝날때마다 동작
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test       //테스트 코드는 과감하게 한글로 작성해보자. 빌드될 때 테스트 코드는 실제 코드에 포함되지 않는다.
    void 회원가입() {
        //given(뭔가 상황이 주어졌을 때 -> 이 데이터를 기반으로 테스트)
        Member member = new Member();
        member.setName("Hello");

        //when(이러한 상황에서 실행했을 때 -> 이걸 검증한다.)
        Long saveId = memberService.join(member);

        //then(결과가 이래야 해)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //뒤의 로직을 실행해야 하는데, 앞의 에러가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThrows(NullPointerException.class, () -> memberService.join(member2)); -> 실패

        assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

        /* try-catch를 사용한 일반적인 방법
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1111"); -> 실패
        }
        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
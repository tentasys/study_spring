package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//이렇게 DB와 연동해서 하는 것을 통합 테스트라 함
//통합 테스트의 경우, 시간이 오래 걸림

@SpringBootTest     //테스트를 할 때 스프링부트를 포함
@Transactional      //이 어노테이션을 테스트 시작 전에 달면 테스트 후 디비 변경사항이 롤백된다
class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository; //인터페이스로 가져오기 -> springconfig에서 jdbc로 세팅해준다
    @Autowired MemberService memberService;

    @Test
    //@Commit //이게 있으면 transactional이어도 db에 반영이 된다
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring100");

        //when
        Long saveId = memberService.join(member);

        //then
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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

        //then
    }
}
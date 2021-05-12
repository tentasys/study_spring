package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //직접 생성하는 것이 아니라 외부에서 넣게끔 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 안 된다.
        validateDuplicatedMember(member);   //중복 회원 증


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        //optional이니까 가능한 것. 값이 있으면 아래의 내용이 동작을 한다.
        //optional 안에 멤버 객체가 있는 것이므로, optional을 통해 여러 가지를 할 수 있다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
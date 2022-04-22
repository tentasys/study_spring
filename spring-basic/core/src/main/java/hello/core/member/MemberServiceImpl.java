package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //이전에는 객체를 생성하고 이 인터페이스에 어떤 것이 들어가야할지를 MemberService 내에서 직접 했었음
    // -> 배우가 직접 담당배우 섭외하는거랑 같음
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //memberRepository에 무엇이 들어갈지를 생성자를 통해서 넣어준다.
    //MemoryMemberRepository에 대한 코드가 없음 -> 추상화에만 의존 -> DIP를 지킨다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

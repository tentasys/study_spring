package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//회원 저장소의 구현체 - 데이터베이스가 아직 확정되지 않았기 때문에 메모리 상에서만 띄운다.
public class MemoryMemberRepository implements MemberRepository{

    //사실 동시성 문제가 있기 때문에 ConcurrentHashMap을 사용해야 한다.
    private static Map<Long, Member> store = new HashMap();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

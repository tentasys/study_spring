package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 동시성 문제를 고려하여 작성해야 하지만 생략한다.
    //실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static Map<Long, Member> store = new HashMap<>();   //얘가 static이라서 클래스 단에 붙는다.
    private static long sequence = 0L;

    @Override   //저장하기 전에 시퀀스 증가하고 넣음
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null이 반환될 가능성이 있다면 optional로 감싸는 추세
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //java 람다
        //루프를 돌면서 찾으면 반환하고 없으면 null로 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))     //member의 name이 파라미터로 넘어온 name이랑 같은지 확인. 같은 경우에만 필터링이 된다.
                .findAny();     //findAny는 하나라도 찾으면 반환되는 것으로, optional로 반환된다.
    }

    @Override
    public List<Member> findAll() {
        //store에 있는 member들을 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); //.clear하면 스토어를  다 비움
    }
}

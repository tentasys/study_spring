package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //jpa는 entitymanager로 모든 것이 동작한다.
    //data-jpa 라이브러리를 받았기 때문에 spring boot가 실행될 때 자동으로 entitymanager를 만든다.
    private final EntityManager em;

    //결론 -> jpa를 쓰려면 entitymanager를 주입받아야 한다.

    //springboot가 자동으로 만들어 주기 때문에 ,만든 것을 injection 받으면 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}

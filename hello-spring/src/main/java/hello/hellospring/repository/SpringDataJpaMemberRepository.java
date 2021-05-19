package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스를 받을때는 extends 사용
//인터페이스는 다중상속이 된다
//springdatajpa가 jparepository를 받고 있으면 구현체를 자동으로 만들어 줌. 스프링 빈에 자동으로 등록해 준다.
//내가 스프링 빈에 등록하는 것이 아니라, springdatajpa가 자동으로 구현체를 만들어서 등록함
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByName(String name);
    //규칙을 따르면 됨 -> 인터페이스 이름만으로도 개발할 수 있다!
    //findBy 뒤에 있는 걸 가져다가 -> select m from Member m where m.name = ? 과 같이 짬
}

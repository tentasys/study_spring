package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;    //option + enter -> static import

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 동작의 순서는 없다. 모든 메소드가 다 따로 동작하도록 설계해야 한다. -> 순서 의존적으로 설계하면 절대 안됨
    //findall부터 실행될 경우 -> spring1, spring2를 먼저 저장했는데 다른 객체가 나와버림
    //테스트가 끝나면 데이터를 클리어해줘야 한다.

    //중요 포인트 : 테스트는 서로 의존 관계가 없이 설계가 되어야 하며, 그러기 위해선 테스트가 끝날때마다 공용 데이터를 지워줘야 한다.

    @AfterEach //어떤 메소드가 끝날때마다 동작
    public void afterEach(){
       repository.clearStore();
    }

    @Test
    public void save(){
        //main method 작성하는것이랑 비슷하다.
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //optional에서 값을 꺼낼 때는 get으로 꺼낼 수 있다.
        Member result = repository.findById(member.getId()).get();

        //내가 넣은 것과 메모리에 저장된 것이 똑같으면 참
        //soutv -> System.out.println을 자동으로 완성해줌
        //System.out.println("result = " + (result == member));

        //글자로 매번 확인할 수 없음 -> assert 사용
        Assertions.assertEquals(member, result);  //성공 예제 1
        //Assertions.assertEquals(member, null);  //실패 예제
        assertThat(member).isEqualTo(result);   //성공 예제 2 -> 이게 더 잘 쓰임
        //assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

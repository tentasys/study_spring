package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired //DI해줌
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    //@Autowired
    //SpringConfig에서 memberrepository를 찾는다. -> 등록한 게 없는데? -> SpringdatajpamemberRepository가 있으니까 된다
    //jpaRepository가 다 해줌
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean   //spring bean을 등록할 거야
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    //@Component를 통해 컴포넌트 스캔을 할 수 있지만, 스프링 빈에 직접 등록하면 AOP가 등록되었다는 것을 확실히 알 수 있음
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    //}
}
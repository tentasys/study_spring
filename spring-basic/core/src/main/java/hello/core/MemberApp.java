package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //v1 - 여기서 직접 생성함
        //MemberService memberService = new MemberServiceImpl();

        //v2 - appconfig를 이용한 애플리케이션 개발
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //v3 - 스프링 컨테이너를 사용
        //스프링의 모든 것은 ApplicationContext로 시작한다. 얘가 스프링 컨테이너라고 보면 된다.
        //얘가 모든 객체들을 관리해준다(@Bean)
        //어노테이션 기반으로 config 사용 - AnnotaionConfigApplicationContext를 사용/ 파라미터로 클래스명 넣어준다.
        //클래스 내에 빈을 스프링 컨테이너에 집어 넣고 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //기본적으로 메서드명이 등록되고(memberService), 반환타입(MemberService 클래스 타입) 명시
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

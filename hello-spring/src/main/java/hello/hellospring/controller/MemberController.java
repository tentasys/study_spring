package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller     //스프링 컨테이너가 뜰 때 자동으로 생성해줌. 컨트롤러는 스프링에 의해 관리됨. 얘는 어쩔 수 없어서 autowired를 써준다.
public class MemberController {
    private final MemberService memberService;
    //@Autowired private MemberService memberService; // 필드 주입

    @Autowired      //생성자에 autowired라고 되어 있으면  -> 이 멤버 서비스를 스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결시켜준다.
    //연결시켜줄 때, 생성자에서 이 어노테이션을 쓴다.
    //멤버컨트롤러가 생성이 될 때, 스프링빈에 등록된 멤버서비스 객체를 가져다가 넣어준다. -> DI, 의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @Autowired // Setter 주입
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }
}

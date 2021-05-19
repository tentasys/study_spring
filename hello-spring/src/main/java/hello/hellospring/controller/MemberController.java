package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    //get방식을 통해 아래 주소로 매핑이 된다.
    //일반적으로 url을 쳤을 때는 getmapping을 통해 간다.
    //get은 조회할 때 주로 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //데이터를 전달할 때 post를 사용.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    //--> url은 다르지만 get/post에 따라 다르게 매핑할 수 있다.

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //리스트 자체를 모델에 담가 화면에 넘긴다
        //key가 members고 members 안에 리스트로 모든 회원을 다 조회 하여 담아놓았다.
        return "members/memberList";
    }
}

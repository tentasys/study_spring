package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    //여기에서의 get은 get/post의 get이다. 메소드로서의 get
    @GetMapping("hello")    //웹 어플리케이션에서 /hello라고 들어오면 아래의 메소드를 호출해 준다.
    public String hello(Model model){
            model.addAttribute("data", "spring!!");
            return "hello"; //데이터는 모델로 화면에 넘기고, 리소스 중에 hello인 것을 찾아가서 렌더링 해라. -> hello.html 찾아가자
    }
}

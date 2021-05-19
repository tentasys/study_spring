package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //localhost:8080요청이 오면 여기부터 찾아본다 -> 매핑 정보가 있으므로 static/index.html은 사용하지 않는다.
    @GetMapping("/") //첫 화면, localhost:8080으로 들어오면 이 화면이 호출이 된다.
    public String home(){
        return "home";
    }
}

package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //얘는 어쨌든 스프링이 관리. 컴포넌트 스캔으로 올라간다.
public class HelloController {

    //여기에서의 get은 get/post의 get이다. 메소드로서의 get
    @GetMapping("hello")    //웹 어플리케이션에서 /hello라고 들어오면 아래의 메소드를 호출해 준다.
    public String hello(Model model){
            model.addAttribute("data", "spring!!");
            return "hello"; //데이터는 모델로 화면에 넘기고, 리소스 중에 hello인 것을 찾아가서 렌더링 해라. -> hello.html 찾아가자
    }

    @GetMapping("hello-mvc")
                                        //required = true가 기본이고, 파라미터를 넘겨야 한다는 의미.
    public String HelloMVC(@RequestParam(name = "name", required = false) String name, Model model){ //모델이 받으면 그것을 렌더링하는데 사용
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody       //중요함!!
    //http에서의 body부의 이 데이터를 직접 넣어주겠다는 뜻임. 응답 body부에 이 내용을 직접 넣겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;   //hello spring을 요청한 클라이언트에 그대로 내려보낸다.
        //템플릿 엔진과의 차이 : View 이런게 없다. 이 문자 그대로 내려감!
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        //java bean 표준 규약. name을 getter/setter로 접근한다. 프로퍼티 접근 방식이라고도 함.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

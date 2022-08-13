package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor        //생성자에 Autowired 들어가면서 자동 주입된다.
public class LogDemoController {

    private final LogDemoService logDemoService;
    //myLogger를 주입하는게 아니라 Dependency를 Lookup하는 친구가 주입이 된다.
    //private final ObjectProvider<MyLogger> myLoggerProvider;    //얘의 생존 범위는 요청이 들어와야 함 - 스프링을 띄우는 단계에서 http request가 넘어오지 않음. -> Scope 'request' is not active -> Provider 사용
    private final MyLogger myLogger;
    //주입 시점에 주입을 받을 수 있다 -> 바로 사용 가능

    //@Autowired 생성자가 생략됨

    @RequestMapping("log-demo")
    @ResponseBody       //view 화면 없이 문자열을 바로 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //MyLogger myLogger = myLoggerProvider.getObject();           //컨트롤러에 고객의 요청이 옴(HTTP가 살아 있는 상태) -> scope를 사용할 수 있는 상황-> 여기서 호출하기에 꺼낼 수 있다. getobject를 최초로 하는 시점에 만들어진다.
        String requestURL = request.getRequestURL().toString();     //고객이 어떤 url로 요청했는지 알 수 있음

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";
    }
}

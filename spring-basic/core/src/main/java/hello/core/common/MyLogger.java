package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//값이 두 개 이상 들어가면 value= 와 같이 써 주어야 한다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)     //가짜로 프록시를 만든다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"] "+"["+requestURL+"] "+ message);
    }

    @PostConstruct  //고객이 요청이 들어올 때 호출
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope generated : "+this);       //this하면 주소까지 이쁘게 나온다
    }

    @PreDestroy     //고객 요청이 빠져나가면 호출
    public void close() {
        System.out.println("["+uuid+"] request scope destroyed : "+this);
    }
}

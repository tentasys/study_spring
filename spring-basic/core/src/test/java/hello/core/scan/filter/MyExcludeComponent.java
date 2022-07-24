package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)   //TYPE은 클래스 레벨에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    //얘가 붙은 것을 컴포넌트 스캔에 제외할 것이다 라는 의미
}

package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //이걸 적어줘야 AoP를 쓸 수 있음.
@Component //이걸로 컴포넌트 스캔 가능함
public class TimeTraceAop {

    //어디에다 적용할 것인지? 공통 관심사 타겟팅이 가능하다.
    //패키지명..클래스명..파라미터
    @Around("execution(* hello.hellospring..*(..))")  //패키지 하위에는 다 적용하라는 의미
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString()); //이 안의 메인메소드를 어떤걸 콜할지 확인

        try {
            //다음 메소드로 진행이 된다
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs + "ms");
        }
    }
}

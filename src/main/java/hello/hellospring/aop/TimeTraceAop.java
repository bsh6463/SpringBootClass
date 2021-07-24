package hello.hellospring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //hellospring하뒤,, 여러옵션 있음.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        log.info("START : {}", joinPoint.toString());

        try{
            Object result = joinPoint.proceed();// 다음 메서드로 진행
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("END : {} {} ms", joinPoint.toString(), timeMs);
        }
    }
}

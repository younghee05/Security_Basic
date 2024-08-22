package com.study.SpringSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// aop 기본 틀 (이렇게 써야함) 클래스명만 달라지는 꼴
// aop 는 filter랑 비슷 하다고 보면 됨

@Aspect
@Component
@Order(value = 2)
public class TestAspect  {

    @Pointcut("execution(String com.study.SpringSecurity.service.TestService.aop*(..))")
    private void pointCut() {}


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // around의 전처리가 실행
        System.out.println("전처리");
        Object result = proceedingJoinPoint.proceed(); // 핵심기능 호출
        System.out.println("후처리"); // 여기가 실행 된 후

        return result; // aopTest라는 메소드를 호출해낸다
    }
}

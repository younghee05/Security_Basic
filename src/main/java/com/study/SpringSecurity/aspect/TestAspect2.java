package com.study.SpringSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 1)
public class TestAspect2 {

    @Pointcut("@annotation(com.study.SpringSecurity.aspect.annotation.Test2Aop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // getArgs 는 메소드를 의미 / Object [] 을 리턴함
//        for (Object obj : proceedingJoinPoint.getArgs()) {
//            System.out.println(obj);
//        }
        // Signature 객체
//        Signature signature = proceedingJoinPoint.getSignature();
        // signature 객체에서 다운캐스팅해줘야 한다
        CodeSignature signature = (CodeSignature) proceedingJoinPoint.getSignature();
        System.out.println(signature.getName());

        Object[] args = proceedingJoinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            System.out.println(paramNames[i] + ": " + args[i]);
        }

        System.out.println("전처리2");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("후처리2");

        return result;
    }
}

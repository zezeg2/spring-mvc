package org.example.aop.aop_annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class Common {
    long start;
    @Pointcut("execution(public * org.example.aop.aop_annotation.*.*(..))")
    public void pointcut(){}
//    @Before("pointcut()")
    public void a(){
        start = System.currentTimeMillis();
        System.out.println("+++++++ => a 메소드는 포인트컷 메소드 실행 이전에 호출" + new Date(start));
    }

//    @After("pointcut()")
    public void b(){
        start = System.currentTimeMillis();
        System.out.println("+++++++ =>b 메소드는 포인트컷 메소드 실행 이후에 호출 " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("==============================================");
    }

    @Around("pointcut()")
    public void c(ProceedingJoinPoint joinPoint) throws Throwable {
        a();
        Object[] args = joinPoint.getArgs();
        for (Object o : args){
            System.out.println("arg : " + o);
        }
        Object proceed = joinPoint.proceed();
        System.out.println(proceed);
        b();
    }
}

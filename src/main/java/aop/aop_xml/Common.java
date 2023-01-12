package aop.aop_xml;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

public class Common {
    long start;
    public void a(){
        start = System.currentTimeMillis();
        System.out.println("+++++++ => a 메소드는 포인트컷 메소드 실행 이전에 호출" + new Date(start));
    }

    public void b(){
        start = System.currentTimeMillis();
        System.out.println("+++++++ =>b 메소드는 포인트컷 메소드 실행 이후에 호출 " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("==============================================");
    }

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

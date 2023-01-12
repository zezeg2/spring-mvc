package aop.animal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AnimalAspect {
    @Around("execution(public * aop.animal.*.*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String animalName = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("점심 머먹었노? " + animalName + "려나");
        Object result = joinPoint.proceed();
        System.out.println(animalName + " 님 " + result + " 먹었군요");
        System.out.println("------------------------------------------");
    }
}

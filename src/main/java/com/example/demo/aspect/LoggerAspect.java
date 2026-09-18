package com.example.demo.aspect;

    import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(* com.example.demo..*.*(..))")
    public void serviceMethods1(){

    }

    @Pointcut("execution(* com.example.demo..*.*(..)) && !within(com.example.demo.security..*)")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint){
        String className=joinPoint.getTarget().getClass().getSimpleName();
        String methodName=joinPoint.getSignature().getName();

        System.out.println("Entering the controller "+className+" "+methodName);
    }
}

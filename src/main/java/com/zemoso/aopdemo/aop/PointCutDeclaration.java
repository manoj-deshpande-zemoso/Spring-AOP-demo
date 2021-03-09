package com.zemoso.aopdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class PointCutDeclaration {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(* com.zemoso.aopdemo.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        logger.info("=====>> in @Before: calling method: " + method);
        logger.info(Arrays.toString(joinPoint.getArgs()));
    }

    @Around("execution(* com.zemoso.aopdemo.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Around: calling method: " + method);
        logger.info(Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed(joinPoint.getArgs());
        logger.info("Returning " + result);
        return result;

    }

    @After("execution(* com.zemoso.aopdemo.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @After: calling method: " + method);
        logger.info(Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* com.zemoso.aopdemo.controller.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterThrowing: calling method: " + method);
        logger.info(Arrays.toString(joinPoint.getArgs()));
        logger.severe(Arrays.toString(ex.getStackTrace()));
    }

}

package com.poc.userdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Before(value = "execution(* com.poc.userdemo.*..*(..))")
	public void beforeMethodExe(JoinPoint joinPoint) {
		LOGGER.debug("START :: Method {}.{}()", joinPoint.getTarget().getClass().getSimpleName(), 
				((MethodSignature)joinPoint.getStaticPart().getSignature()).getMethod().getName());
	}
	
	@After(value = "execution(* com.poc.userdemo.*..*(..))")
	public void afterMethodExe(JoinPoint joinPoint) {
		LOGGER.debug("END :: Method {}.{}()", joinPoint.getTarget().getClass().getSimpleName(), 
				((MethodSignature)joinPoint.getStaticPart().getSignature()).getMethod().getName());
	}
	
	@Pointcut("@annotation(com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand)")
	public void serviceHystrixCommandPointcut() {}
	
	@Around("serviceHystrixCommandPointcut()")
	public Object doTracingForService(ProceedingJoinPoint serviceJoinPoint) throws Throwable {
		
		Object response = null;
		StopWatch stopWatch = new StopWatch();
	    stopWatch.start();
	    try {
	    	response = serviceJoinPoint.proceed();
	    } finally {
	    	stopWatch.stop();
	    }
	    
	    long time = stopWatch.getLastTaskTimeMillis();
	    LOGGER.info("Total time taken for execution : {}.{}() -> {}ms", serviceJoinPoint.getTarget().getClass().getSimpleName(), 
	    		((MethodSignature) serviceJoinPoint.getSignature()).getMethod().getName(), time);

	    return response;
	}
	
}

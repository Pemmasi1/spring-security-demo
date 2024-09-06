package com.siva.spring_security_demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	// Define a pointcut for any method within the specified packages
	@Pointcut("execution(* com.siva.spring_security_demo.controller..*(..)) || "
			+ "execution(* com.siva.spring_security_demo.service..*(..)) || "
			+ "execution(* com.siva.spring_security_demo.repository..*(..))")
	public void applicationPackagePointcut() {
		// Pointcut definition
	}

	// Before advice: log method entry details
	@Before("applicationPackagePointcut()")
	public void logBeforeMethodExecution(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();
		logger.info("Entering method: {}.{} with arguments {}", className, methodName, args);
	}

	// After returning advice: log method return details
	@AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
	public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		logger.info("Method: {}.{} executed successfully, returned: {}", className, methodName, result);
	}

	// After throwing advice: log method exceptions
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "exception")
	public void logMethodException(JoinPoint joinPoint, Throwable exception) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		logger.error("Exception in method: {}.{} with message: {}", className, methodName, exception.getMessage(),
				exception);
	}

}

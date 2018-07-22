package com.sun.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationAop {
	Logger logger = LoggerFactory.getLogger(ApplicationAop.class);
	@Around("execution(* com.sun.service..*.*(..))")
	public Object arroundInvoke(ProceedingJoinPoint point) throws Throwable{
		//取得参数
		logger.info("执行方法之前的参数是："+Arrays.toString(point.getArgs()));
		//执行目标方法
		Object result = point.proceed();
		//将目标方法返回值继续返回给调用处
		logger.info("执行方法后取得返回值："+result);
		return result;
	}
}



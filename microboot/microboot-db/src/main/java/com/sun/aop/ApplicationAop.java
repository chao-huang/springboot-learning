package com.sun.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.db.DS;
import com.sun.db.DataSourceContextHolder;

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
	@Before("@annotation(ds)")  
    public void beforeSwitchDS(JoinPoint point, DS ds){  
        //获得当前访问的class  
        Class<?> className = point.getTarget().getClass();  
        //获得访问的方法名  
        String methodName = point.getSignature().getName();  
        //得到方法的参数的类型  
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();  
        String dataSource = DataSourceContextHolder.DEFAULT_DS;  
        try {  
            // 得到访问的方法对象  
            Method method = className.getMethod(methodName, argClass);  
            // 判断是否存在@DS注解  
            if (method.isAnnotationPresent(DS.class)) {  
                ds = method.getAnnotation(DS.class);  
                //取出注解中的数据源名  
                dataSource = ds.value();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        // 切换数据源  
        DataSourceContextHolder.setDB(dataSource);  
    }  
      
    @After("@annotation(ds)")  
    public void afterSwitchDS(JoinPoint point, DS ds){  
        DataSourceContextHolder.clearDB();  
    } 
}



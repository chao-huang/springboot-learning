package com.sun.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 在spring boot中的FilterRegistrationBean注册过滤器的类中有个order属性,
 * 这个order的默认值是Integer.MAX_VALUE 也就是int的最大值
 * spring boot 会按照order值的大小，从小到大的顺序来依次过滤
 * @author Administrator
 *
 */
@Configuration
public class FilterConfiguration {
	
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		// 注入过滤器
		registration.setFilter(getEncodingFilter());
		// 拦截规则
		registration.addUrlPatterns("/*");
		// 过滤器名称
		registration.setName("EncodingFilter");
		// 是否自动注册 false 取消Filter的自动注册
		//registration.setEnabled(false);
		// 过滤器顺序
		registration.setOrder(1);
		return registration;
	}
	@Bean(name="EncodingFilter")
	public Filter getEncodingFilter(){
		return new EncodingFilter();
	}
}

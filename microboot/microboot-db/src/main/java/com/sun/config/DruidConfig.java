package com.sun.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@Configuration
public class DruidConfig {
	@Bean // 表示项目支持Druid的性能检测
	public ServletRegistrationBean getServletRegistrationBean() {
		// "/druid/*:如果页面进入Druid的监控界面登录则需要访问该路径"
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		bean.addInitParameter("allow", "127.0.0.1,192.168.24.9");// 白名单
		bean.addInitParameter("deny", "192.168.180.200");// 黑名单
		bean.addInitParameter("loginUsername", "sun");// 用户
		bean.addInitParameter("loginPassword", "java");// 密码
		bean.addInitParameter("resetEnable", "false");// 表示不允许重置数据源
		return bean;

	}

	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addUrlPatterns("/*");// 指定要检查的该路径
		bean.setFilter(new WebStatFilter());
		// 不需要监控的资源
		bean.addInitParameter("exclusions", "*.jpg,*.png,*.gif,*.js,*.ico,*.js,*.css,/druid/*");
		
		return bean;
	}

	/*// 需要使用到连接池的相关信息
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDataSource() {
		return  new DruidDataSource();
	}*/
}

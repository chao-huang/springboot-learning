package com.sun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sun.scheding.ScheduledUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@EnableScheduling //声明定时任务
public class SwaggerApplication {
	public static void main(String [] args){
		SpringApplication.run(SwaggerApplication.class, args);
	}
}

package com.sun;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@MapperScan("com.sun.dao")
@EnableCaching
public class AplicationController {
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(AplicationController.class, args);
	    }
	
}

package com.sun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.sun.dao")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DBApplication {
	public static void main(String [] args){
		SpringApplication.run(DBApplication.class, args);
	}
}

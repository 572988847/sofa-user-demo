package com.demo;

import com.demo.service.impl.UserServiceBoltImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.demo.dao")
public class Springboot2MybatisDemoApplication {

	public static void main(String[] args) {

	//	SpringApplication.run(Springboot2MybatisDemoApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(
				Springboot2MybatisDemoApplication.class, args);

		UserServiceBoltImpl personBolt = (UserServiceBoltImpl) applicationContext
				.getBean("userServiceBoltImpl");
		System.out.println(personBolt.queryUserById(1009));
	}
}

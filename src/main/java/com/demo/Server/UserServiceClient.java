package com.demo.Server;

import com.demo.model.UserDomain;
import com.demo.service.impl.UserServiceBoltImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo.service.impl")
@MapperScan("com.demo.dao")
public class UserServiceClient {


    public static void main(String[] args) {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName("张三");
        userDomain.setPhone("18800000017");
        userDomain.setPassword("a0000000WQ");
        System.setProperty("server.port", "8082");
        SpringApplication springApplication = new SpringApplication(UserServiceClient.class);

        ApplicationContext applicationContext = springApplication.run(args);

        UserServiceBoltImpl annotationService = applicationContext.getBean(UserServiceBoltImpl.class);

        String result = annotationService.addUser(userDomain);
        System.out.println("invoke result:" + result);




    }
}

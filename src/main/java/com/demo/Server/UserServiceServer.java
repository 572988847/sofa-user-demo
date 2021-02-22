package com.demo.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserServiceServer {
    public static  void  main(String[] args)
    {
        SpringApplication springApplication = new SpringApplication(UserServiceServer.class);
        ApplicationContext applicationContext = springApplication.run(args);
}
}

package com.torres.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication // start Spring Boot application
public class TestSpringBootApplication {

	public static void main(String[] args){
		
		System.out.println("...Test SpringBoot Application start!!...");
		
		ApplicationContext ctx = SpringApplication.run(TestSpringBootApplication.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
			System.out.println(beanName);
		
	}
}

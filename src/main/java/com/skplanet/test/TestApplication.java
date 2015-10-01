package com.skplanet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class TestApplication {

	public static void main(String [] args){
		SpringApplication.run(TestApplication.class, args);
		System.out.println("Spring Boot Start!");
		
	}
}

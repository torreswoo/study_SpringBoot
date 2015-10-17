package com.skplanet.test;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
	
	@RequestMapping("/")
	String root(){
		return "Hello World!";
	}

}

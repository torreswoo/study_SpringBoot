package com.torres.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.torres.springboot.processing.DataIO;
import com.torres.springboot.processing.DataIOElasticSearch;
import com.torres.springboot.processing.DataIOKafka;

@Configuration
public class TestbootConf {
	
	@Bean
	@Autowired
	public DataIO dataIO(){
		return new DataIO();
	}
	
	@Bean
	@Autowired
	public DataIOElasticSearch dataIOElastic(){
		return new DataIOElasticSearch(); 
	}
	
	@Bean
	@Autowired
	public DataIOKafka dataIOKafka(){
		return new DataIOKafka();
	}
	
	

}

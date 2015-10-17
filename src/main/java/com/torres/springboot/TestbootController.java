package com.torres.springboot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.torres.springboot.data.LogDataRaw;
import com.torres.springboot.processing.DataIO;

@Controller
public class TestbootController {
	
	@Autowired
	private DataIO dataIO;
	
	@RequestMapping(value="/")
	public @ResponseBody String testHello(){
		return "Hello World!";
	}

	// /data/{routeType} : elastic / kafka
	// flow=input/output
	// ex) http://localhost:8080/data/kafka?flow=input
	@RequestMapping(value="/data/{routeType}") 
	public @ResponseBody Map<String, Object> dataProcessing(
			@PathVariable String routeType,
			@RequestParam(required = true) String flow			
			){
		
		// routeType에 따라 logic 
		switch(routeType){
		
			case "kafka":  
				System.out.printf("..[%s] %s dataProcessing ...\n", routeType, flow);
				break;
				
			case "elastic":
				System.out.printf("..[%s] %s dataProcessing ...\n", routeType, flow);
				break;
				
			default:
				System.out.println("..end dataProcessing...");
			
		}
		
		return null ;
	}
	
	//.......................................................
	// test input Json data
	@RequestMapping(value="/datainput")
	public @ResponseBody String insertTestData(
			@RequestBody LogDataRaw rawData){
		System.out.println(rawData);
		return "success";
	}

}

package com.torres.springboot.data;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LogDataProcessing {
	private String _index;
	private String _type;
	private List<Map<String, Object>> sourceList; 
	
}

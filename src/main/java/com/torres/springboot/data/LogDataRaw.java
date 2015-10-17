package com.torres.springboot.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LogDataRaw {
	private String _index;
	private String _type;
	private String host;
	private int logObject;
}

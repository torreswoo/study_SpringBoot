package com.torres.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torres.springboot.processing.DataIO;

@Service
public class TestbootService {
	
	@Autowired
	private DataIO dataIO;
}

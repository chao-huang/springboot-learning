package com.sun.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleAction {
	@RequestMapping("/hello")
	public String home(){
		return "hello";
	}
}

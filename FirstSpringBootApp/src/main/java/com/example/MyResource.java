package com.example;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {
	@RequestMapping("/")
	public String getData(){
		return new Date().toString();
	}
}
	
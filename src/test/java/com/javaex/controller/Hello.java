package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello() {
		System.out.println("/phonebook3/hello");
		return "/WEB-INF/views/index.jsp";
	}
}

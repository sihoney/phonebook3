package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/test")
public class Test {
	
	///////////////////////
	// field
	///////////////////////
	
	///////////////////////
	// constructor
	///////////////////////
	
	///////////////////////
	// method
	///////////////////////
	@RequestMapping(value="/print", method=RequestMethod.GET)
	public String testPrint(@RequestParam(value="age", required=false, defaultValue="-1") int age, 
							@RequestParam String name) {
		System.out.println("testPrint" +  " "+ age + " " + name);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/print2/{no}", method=RequestMethod.GET)
	public String testPrint2(@PathVariable("no") int no) {
		System.out.println("testPrint2" + " "+ no);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/index", method= {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		System.out.println("/phonebook3/test");
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public void join(@ModelAttribute UserVo userVo) {
		
	}
}

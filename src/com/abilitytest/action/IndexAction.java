package com.abilitytest.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexAction {
	
	@RequestMapping(value="login")
	public String login(){
		return "login"; 
	}
	
	@RequestMapping(value="register")
	public String register(){
		return "Register"; 
	}
	
	@RequestMapping(value="interfacetest/{path}")
	public String load(@PathVariable String path){
		return "interfacetest/"+path; 
	}
}



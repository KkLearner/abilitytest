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
		return "register"; 
	}
	
	@RequestMapping(value="backstage")
	public String backstage(){
		return "backstage"; 
	}
	
	@RequestMapping(value="adduser")
	public String adduser(){
		return "adduser"; 
	}
	
	@RequestMapping(value="modifypassword")
	public String modifypassword(){
		return "modifypassword"; 
	}
	
	@RequestMapping(value="test1")
	public String test1(){
		return "test1"; 
	}
	
	@RequestMapping(value="interfacetest/{path}")
	public String load(@PathVariable String path){
		return "interfacetest/"+path; 
	}
}



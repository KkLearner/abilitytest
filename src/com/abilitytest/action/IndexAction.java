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
	
	@RequestMapping(value="test2")
	public String test2(){
		return "test2"; 
	}
	
	@RequestMapping(value="test3")
	public String test3(){
		return "test3"; 
	}
	
	@RequestMapping(value="test4")
	public String test4(){
		return "test4"; 
	}
	
	@RequestMapping(value="test5")
	public String test5(){
		return "test5"; 
	}
	
	@RequestMapping(value="test6")
	public String test6(){
		return "test6"; 
	}
	
	@RequestMapping(value="test7")
	public String test7(){
		return "test7"; 
	}
	
	@RequestMapping(value="test8")
	public String test8(){
		return "test8"; 
	}
	
	@RequestMapping(value="test9")
	public String test9(){
		return "test9"; 
	}
	
	@RequestMapping(value="list")
	public String list(){
		return "list"; 
	}
	
	@RequestMapping(value="interfacetest/{path}")
	public String load(@PathVariable String path){
		return "interfacetest/"+path; 
	}
}



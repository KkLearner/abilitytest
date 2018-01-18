package com.abilitytest.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexAction {

	@RequestMapping(value="welcome")
	public String welcome(){
		return "interfacetest/welcome"; 
	}
	
	@RequestMapping(value="test")
	public String test(){
		return "interfacetest/test"; 
	}
	
	@RequestMapping(value="login")
	public String login(){
		return "login"; 
	}
}

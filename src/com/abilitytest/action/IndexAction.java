package com.abilitytest.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexAction {

	@RequestMapping("welcome")
	public String welcome(){
		return "interfacetest/welcome"; 
	}
}

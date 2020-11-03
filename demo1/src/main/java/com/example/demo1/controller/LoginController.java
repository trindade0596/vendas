package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin(origins="*")
public class LoginController {
	
	@GetMapping(path = "/login")
	public String getLoginView() {
		return "login";
	}
	
	@GetMapping("home")
	public String getCourses() {
		return "home";
	}

}

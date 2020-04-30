package com.jpabook.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {
	@RequestMapping("/")
	public String home() {
		System.out.println("HomeController");
		return "home";
	}
}

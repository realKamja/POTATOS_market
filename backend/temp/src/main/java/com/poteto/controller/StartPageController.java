package com.poteto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

	@GetMapping("/") //
	public String loginPage() {
		return "LoginPage"; // 로그인 화면 출력
	}
	
	@GetMapping("/main")
	public String main() {
		return "MainPage";
	}
}

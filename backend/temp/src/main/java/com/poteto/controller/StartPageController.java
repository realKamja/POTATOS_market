package com.poteto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

	@GetMapping("/") //
	public String login() {
		return "login"; // 로그인 화면 출력
	}
}

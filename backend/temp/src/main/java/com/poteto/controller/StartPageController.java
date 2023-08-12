package com.poteto.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartPageController {

	@GetMapping(value = {"/", "/main"})
	public String startPage(HttpSession session) {
		if((String) session.getAttribute("loginId") == null) { // 로그인 되지 않은 사용자 검사
			return "/member/login"; // 로그인창 Url주소(프론트에게 쓰임)
		}
		else { // 로그인된 사용자
			return "메인페이지"; // 메인페이지(아직 구현 안함)
		}
	}


}

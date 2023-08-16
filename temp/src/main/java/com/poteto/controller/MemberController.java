package com.poteto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.poteto.dto.MemberDTO;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// 생성자 주입
	@Autowired
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌

	@PostMapping("/member/login")
	public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if(loginResult != null) {
			// login 성공
			session.setAttribute("loginId", loginResult.getMemberId()); // -> html / session.loginId -> 로그인된 아이디 출력
			
			return "redirect:/main"; // MainPage 이동
		}
		else {
			// login 실패
			return "redirect:/"; // 다시 로그인 페이지로 이동
		}
	}


	// 회원가입 페이지 출력 요청
	@GetMapping("/member/signin")
	public String saveForm() {
		return "SigninPage";
	}

	@PostMapping("/member/signin")
	/*
	public String save(@RequestParam("memberEmail") String memberEmail,
						@RequestParam("memberPassword") String memberPassword,
						@RequestParam("memberName") String memberName) {
						*/
	public String signinForm(@ModelAttribute MemberDTO memberDTO) {
		String checkResult = memberService.IdCheck(memberDTO.getMemberId());

		if (checkResult!=null && checkResult.equals("ok")) {
			memberService.signin(memberDTO);
			return "redirect:/"; //회원가입 성공, 로그인 페이지로 이동
		} else {
			// 중복 아이디가 이미 존재하는 경우에 대한 처리
			return "redirect:/member/signin"; // 회원가입 페이지로 리다이렉트
		}
	}

	@PostMapping("/member/Id-check")
	// ajax를 사용 할 때에는 @ResponseBod를 사용한다.
	public @ResponseBody String IdCheck(@RequestParam("memberId") String memberId) {
		// System.out.println("memberId = " + memberId);

		String checkResult = memberService.IdCheck(memberId);
		return checkResult;
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}

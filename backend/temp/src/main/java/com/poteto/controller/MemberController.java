package com.poteto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poteto.dto.MemberDTO;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// 생성자 주입
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	
	@PostMapping("/member/login")
	public String loginForm(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if(loginResult != null) {
			// login 성공
			session.setAttribute("loginId", loginResult.getMemberId());
			return "main";
		}
		else {
			// login 실패
			return "login";
		}
	}
	
	// 회원가입 페이지 출력 요청
	@GetMapping("/member/signin")
	public String saveForm() {
		return "signin";
	}
	
	@PostMapping("/member/signin")
	/*
	public String save(@RequestParam("memberEmail") String memberEmail,
						@RequestParam("memberPassword") String memberPassword,
						@RequestParam("memberName") String memberName) {
						*/
	public String signin(@ModelAttribute MemberDTO memberDTO) {
	    String checkResult = memberService.IdCheck(memberDTO.getMemberId());
	    
	    if (checkResult!=null && checkResult.equals("ok")) {
	        memberService.signin(memberDTO);
	        return "login";
	    } else {
	        // 중복 아이디가 이미 존재하는 경우에 대한 처리
	    	return "redirect:/member/signin"; // 회원가입 페이지로 리다이렉트
	    }
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/member/Id-check")
	// ajax를 사용 할 때에는 @ResponseBod를 사용한다.
	public @ResponseBody String IdCheck(@RequestParam("memberId") String memberId) {
		// System.out.println("memberId = " + memberId);
		
		String checkResult = memberService.IdCheck(memberId);
		return checkResult;
	}
}

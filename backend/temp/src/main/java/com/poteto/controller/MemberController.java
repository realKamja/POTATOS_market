package com.poteto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.poteto.dto.MemberDTO;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class MemberController {
	
	// 생성자 주입
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌

	@PostMapping("/member/login")
	public String loginForm(@RequestBody MemberDTO memberDTO, HttpSession session) { //@Request피라미터: Http Body의 값을 자바객체로 변환
		MemberDTO loginResult = memberService.login(memberDTO);
		if(loginResult != null) {
			// login 성공
			session.setAttribute("loginId", loginResult.getMemberId());
			String memberId = (String) session.getAttribute("loginId");
			return memberId; //사용자의 아이디 반환
		}
		else {
			// login 실패
			return null; // 아무정보 반환 하지 않음
		}
	}

	/* rest api 에서는 프론트의 영역
	// 회원가입 페이지 출력 요청
	@GetMapping("/member/signin")
	public String saveForm() {
		return "signin";
	}
	 */

	@PostMapping("/member/signin")
	/*
	public String save(@RequestParam("memberEmail") String memberEmail,
						@RequestParam("memberPassword") String memberPassword,
						@RequestParam("memberName") String memberName) {
						*/
	public String signin(@RequestBody MemberDTO memberDTO) {
		String checkResult = memberService.IdCheck(memberDTO.getMemberId());

		if (checkResult!=null && checkResult.equals("ok")) {
			memberService.signin(memberDTO);
			return "success"; //회원가입 성공
		} else {
			// 중복 아이디가 이미 존재하는 경우에 대한 처리
			return "fail"; // 회원가입 페이지로 리다이렉트
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
	public void logout(HttpSession session) {
		session.invalidate();
	}
}

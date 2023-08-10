package com.poteto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poteto.dto.BuyerDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.sevice.BuyerService;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BuyController {
	
	// 생성자 주입
	private final BuyerService buyerService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	
	@GetMapping("/buyerform")
	public String buyerform(){
		return "buyform";
	}
	
	@PostMapping("/buyform")
	public String buyInformationSave(BuyerDTO buyerDTO, HttpSession session) {
		String loggedInUsername = (String) session.getAttribute("loginId");
	    MemberEntity loggedInMember = memberService.findByMemberId(loggedInUsername);

	    buyerDTO.setLoggedInMember(loggedInMember);

	    buyerService.buyInformationSave(buyerDTO, session);

	    return "main";
	}
}

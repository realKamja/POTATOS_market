package com.poteto.controller;


import com.poteto.dto.BuyerDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import com.poteto.sevice.BuyerService;
import com.poteto.sevice.MemberService;
import com.poteto.sevice.ProducterService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BuyerController {
	
	// 생성자 주입
	private final BuyerService buyerService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	private final ProducterService producterService;

	@GetMapping("/main/{id}/buyerform")
	public String buyerform(HttpSession session){
		String loggedInUsername = (String) session.getAttribute("loginId");
		if (loggedInUsername==null){
			return "login"; //로그인 되지 않았을 시 로그인 페이지로 이동
		}
		return "buyform";
	}
	
	@PostMapping("/main/{id}/buyerform")
	public String buyInformationSave(BuyerDTO buyerDTO, HttpSession session, @PathVariable Long id) {
		String loggedInUsername = (String) session.getAttribute("loginId");

	    MemberEntity loggedInMember = memberService.findByMemberId(loggedInUsername);
		buyerDTO.setLoggedInMember(loggedInMember);

		ProducterEntity product = producterService.show(id);
		buyerDTO.setProduct(product);

		buyerService.buyInformationSave(buyerDTO);

		return "main";
	}
}
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

	@GetMapping("/main/{id}/buyerform")//판매자의 정보 product_id를 url의 파라미터로 가져와 buyer테이블에 저장 할 수 있게 경로를 이렇게 바꿨습니다
	public String buyerform(HttpSession session){
		String loggedInUsername = (String) session.getAttribute("loginId");
		if (loggedInUsername==null){
			return "login"; //로그인 되지 않았을 시 로그인 페이지로 이동
		}
		return "buyform";
	}
	
	@PostMapping("/main/{id}/buyerform")
	// 프론트에서 {id}값이 고유히 살아 있는채로 post호출을 하기 위해 /buyform -> getmapping함수와 동일한 경로로 바꿨습니다
	// 제 짧은 프론트 지식으로는 이게 최선 이었어서...
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
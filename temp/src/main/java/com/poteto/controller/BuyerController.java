package com.poteto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.poteto.dto.BuyerDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.ProducterRepository;
import com.poteto.sevice.BuyerService;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BuyerController {
	
	// 생성자 주입
	@Autowired
	private final BuyerService buyerService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	
	@Autowired
	private final MemberService memberService; // -> service클레스에 있는 자원을 사용할 권한을 줌
	
	@Autowired
	private ProducterRepository producterRepository;
	
	@GetMapping("/main/sale/{id}/purchaseform")
	public String buyerform(@PathVariable Long id, Model model){
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);
		
		model.addAttribute("producter", producterEntity);
		
		return "PurchaseForm";
	}
	
	@PostMapping("/main/sale/{id}/purchaseform")
	public String buyInformationSave(BuyerDTO buyerDTO, HttpSession session, @PathVariable Long id) {

		String loggedInUsername = (String) session.getAttribute("loginId");
	    MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
	    
	    buyerDTO.setLoggedInMember(loggedInMemberId);
	    ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);
	    buyerDTO.setSaleTitle(producterEntity);
	    
	    buyerService.buyInformationSave(buyerDTO, session);

	    return "redirect:/main/sale/" + id;
	}
}
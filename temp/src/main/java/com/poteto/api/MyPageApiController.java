package com.poteto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poteto.entity.MemberEntity;
import com.poteto.repository.BuyerRepository;
import com.poteto.repository.ProducterRepository;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MyPageApiController {
	
	@Autowired
    private MemberService memberService;
	
	@Autowired
    private ProducterRepository producterRepository;
	
	@Autowired
    private BuyerRepository buyerRepository;
	
	@PostMapping("/saleTitle")
    public ResponseEntity<List<String>> saleItemTitle(HttpSession session) {
		
		// 로그인한 멤버 정보 가져오기
    	String loggedInUsername = (String) session.getAttribute("loginId");
    	MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
		
        List<String> saleTitle = producterRepository.findSaleItemTitle(loggedInMemberId.getId());
        
        return ResponseEntity.ok(saleTitle);
    }
	
	@PostMapping("/purchaseTitle")
    public ResponseEntity<List<String>> purchaseItemTitle(HttpSession session) {
		
		// 로그인한 멤버 정보 가져오기
    	String loggedInUsername = (String) session.getAttribute("loginId");
    	MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
    	
    	List<String> purchaseTitle = buyerRepository.findSaleId(loggedInMemberId.getId());

        System.out.println("f2: " + purchaseTitle);
        return ResponseEntity.ok(purchaseTitle);
    }
}

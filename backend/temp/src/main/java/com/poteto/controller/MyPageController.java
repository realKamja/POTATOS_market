package com.poteto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.BuyerRepository;
import com.poteto.repository.ProducterRepository;
import com.poteto.sevice.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {
	
	@Autowired
	private ProducterRepository producterRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private MemberService memberService;
	
    @GetMapping("/mypage")
    public ModelAndView mypage(HttpSession session, Model model) {
        // 세션에서 로그인된 멤버 정보 가져오기
        String loggedInUsername = (String) session.getAttribute("loginId");
		MemberEntity loggedInMember = memberService.findByMemberId(loggedInUsername);

        // 로그인된 멤버의 Producter와 BuyerEntity 정보 가져오기
        List<ProducterEntity> producterList = producterRepository.findByLoggedInMember(loggedInMember);
        List<BuyerEntity> buyerList = buyerRepository.findByLoggedInMember(loggedInMember);

        ModelAndView modelAndView = new ModelAndView("MyPage");
        modelAndView.addObject("producterList", producterList);
        modelAndView.addObject("buyerList", buyerList);
        return modelAndView;
    }
}

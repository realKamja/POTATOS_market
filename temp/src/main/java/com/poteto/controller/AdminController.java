package com.poteto.controller;

import com.poteto.dto.MemberForAdminDTO;
import com.poteto.entity.BuyerEntity;
import com.poteto.sevice.BuyerService;
import com.poteto.sevice.MemberService;
import com.poteto.sevice.ProducterService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping()
public class AdminController {

    private final MemberService memberService;
    private final ProducterService producterService;
    private final BuyerService buyerService;

    @GetMapping("/admin")
    public String adminPage(HttpSession session) {
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시 true httpbody에 true반환
                return "admin_main";
            }
        } catch (Exception e) { // 로그인되지 않은 사용자 접근시
            e.getMessage();
            return "no_access";
        }
        return "no_access"; // 로그인된 일반 사용자 접근시
    }

    @GetMapping("/admin/members")
    public String membersForAdmin(Model model, HttpSession session) {
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시
                List<MemberForAdminDTO> memberList = memberService.membersForAdmin(); // 회원 정보 반환
                model.addAttribute("memberList", memberList);
                return "admin_memberList";
            }
        } catch (Exception e) { // 로그인되지 않은 사용자 접근시
            return "no_access";
        }
        return "no_access"; // 로그인된 일반 사용자 접근시
    }

    // product 테이블들 다 가져오기
    @GetMapping("/admin/producters")
    public String getProducterList(Model model, HttpSession session) {
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시
                model.addAttribute("producterList", producterService.index());
                return "admin_producterList"; // 회원 정보 반환
            }
        } catch (Exception e) { // 로그인되지 않은 사용자 접근시
            return "no_access";
        }
        return "no_access"; // 로그인된 일반 사용자 접근시
    }
    
    @PatchMapping("/main/{id}")
    public void adminGoOver(@PathVariable Long id) {
        producterService.updateAdminGoOver(id);
    }
}
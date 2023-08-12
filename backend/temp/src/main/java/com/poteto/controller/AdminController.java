package com.poteto.controller;

import com.poteto.dto.MemberForAdminDTO;
import com.poteto.sevice.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;

    @GetMapping()
    public String adminPage(HttpSession session){
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시
                return "/admin/main"; //관리자 메인 페이지 주소 반환(아직 구현 안함)
            }
        } catch(Exception e){ // 로그인되지 않은 사용자 접근시
            e.getMessage();
            return "/main"; //(메인페이지 아직 구현 안함)
        }
        return "/main"; // 로그인된 일반 사용자 접근시
    }

    @GetMapping("/members")
    public List<MemberForAdminDTO> membersForAdmin(HttpSession session){
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시
                return memberService.membersForAdmin(); // 회원 정보 반환
            }
        } catch(Exception e){ // 로그인되지 않은 사용자 접근시
            e.getMessage();
        }
        return null; // 로그인된 일반 사용자 접근시
    }

}

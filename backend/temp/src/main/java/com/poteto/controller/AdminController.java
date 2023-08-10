package com.poteto.controller;

import com.poteto.sevice.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;

    @GetMapping()
    public String adminPage(HttpSession session){
        try {
            boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
            if (isAdmin == true) { // 로그인된 관리자 접근시
                return "admin_main";
            }
        } catch(Exception e){ // 로그인되지 않은 사용자 접근시
            e.getMessage();
            return "no_access";
        }
        return "no_access"; // 로그인된 일반 사용자 접근시
    }

}

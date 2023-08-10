package com.poteto.controller;

import com.poteto.sevice.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;

    @GetMapping()
    public String adminPage(HttpSession session){
        boolean isAdmin = memberService.isAdmin((String) session.getAttribute("loginId"));
        if (isAdmin == true){
            return "admin_main";
        }
        else {
            return "redirect:/main";
        }
    }

}

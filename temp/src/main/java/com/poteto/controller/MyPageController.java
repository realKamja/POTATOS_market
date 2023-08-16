package com.poteto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyPageController {
	
	@GetMapping("/main/mypage")
    public String mypage() {
    	
        return "MyPage";
    }
	
	@GetMapping("/main/mypage/salestatus")
    public String salstatus() {
    	
        return "SaleStatus";
    }
	
	@GetMapping("/main/mypage/purchasehistory")
    public String purchasehistory() {
    	
        return "PurchaseHistory";
    }
}

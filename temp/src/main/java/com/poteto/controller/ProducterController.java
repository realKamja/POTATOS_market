package com.poteto.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poteto.dto.ProducterDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.ProducterRepository;
import com.poteto.sevice.MemberService;
import com.poteto.sevice.ProducterService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProducterController {
	
	@Autowired
	private ProducterRepository producterRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProducterService producterService;
	
	@GetMapping("/main/saleform")
	public String slaeform() {
		return "SaleForm";
	}
	
	@PostMapping("/main/saleform")
	public String createSlaeForm(ProducterDTO form, @RequestParam("ImageFile") MultipartFile imageFile, HttpSession session) {
		
		// 로그인한 멤버 정보 가져오기
		String loggedInUsername = (String) session.getAttribute("loginId");
	    MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
		
		ProducterEntity producterEntity = form.toEntity(form);
		
		if (!imageFile.isEmpty()) {
			String fileExtension = imageFile.getOriginalFilename();
		    String fileName = UUID.randomUUID().toString() + "_" + fileExtension;
			producterEntity.setProducterImage(fileName);

	        try {
	            Path filePath = Paths.get("src/main/resources/static/images/" + fileName);
	            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		producterEntity.setLoggedInMember(loggedInMemberId);
		ProducterEntity saved = producterRepository.save(producterEntity);
		
		return "redirect:/main/sale/" + saved.getId();
	}
	
	@GetMapping("/main/sale/{id}")
	public String show(@PathVariable Long id, Model model, MemberEntity memberEntity, HttpSession session) {
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);
		
		// 로그인한 멤버 정보 가져오기
		String loggedInUsername = (String) session.getAttribute("loginId");
		MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
			    
		model.addAttribute("producter", producterEntity);
		model.addAttribute("member", loggedInMemberId);
		
		return "SaleShow";
	}
	
	@GetMapping("/main/sale/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);

		model.addAttribute("producter", producterEntity);
		return "SaleEdit";
	}
	
	@PostMapping("/main/sale/{id}/update")
	public String update(ProducterDTO form, @RequestParam("ImageFile") MultipartFile imageFile, HttpSession session) {

		// 로그인한 멤버 정보 가져오기
		String loggedInUsername = (String) session.getAttribute("loginId");
		MemberEntity loggedInMemberId = memberService.findByMemberId(loggedInUsername);
		
		ProducterEntity producterEntity = form.toEntity(form);
		
		if (!imageFile.isEmpty()) {
	        String fileName = imageFile.getOriginalFilename();
	        producterEntity.setProducterImage(fileName);

	        try {
	            Path filePath = Paths.get("src/main/resources/static/images/" + fileName);
	            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		producterEntity.setLoggedInMember(loggedInMemberId);
		
		ProducterEntity target = producterRepository.findById(producterEntity.getId()).orElse(null);
		if(target != null) {
			producterRepository.save(producterEntity);
		}
		
		return "redirect:/main/sale/" + producterEntity.getId();
	}
	
	@GetMapping("/main/sale/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		
		ProducterEntity target = producterRepository.findById(id).orElse(null);
		
		if(target != null) {
			producterRepository.delete(target);
			rttr.addFlashAttribute("msg", "Deletion is complete");
		}
		
		return "redirect:/main";
	}
	
	@PostMapping("/updateAdminGoOver/{productId}")
    @ResponseBody
    public String updateAdminGoOver(@PathVariable Long productId) {
        try {
            producterService.updateAdminGoOver(productId);
            return "success";
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }
}

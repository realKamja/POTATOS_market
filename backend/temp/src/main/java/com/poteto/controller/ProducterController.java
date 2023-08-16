package com.poteto.controller;

import com.poteto.dto.ProducterDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.ProducterRepository;
import com.poteto.sevice.MemberService;
import com.poteto.sevice.ProducterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ProducterController {
	
	@Autowired
	private ProducterRepository producterRepository;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProducterService producterService;
	
	@GetMapping("/producterform")
	public String newArticleForm(HttpSession session) {
		if (session.getAttribute("loginId") == null){ //로그인 되지 않았으면 로그인 창으로 이동
			return "login";
		}
		return "producterform";
	}
	
	@PostMapping("/producterform")
	public String createAricle(ProducterDTO form, @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {
		MemberEntity loggedInMember;
		try{
			loggedInMember = memberService.findByMemberId((String) session.getAttribute("loginId"));
		} catch(Exception e){
			return "login"; // 디비에 존재 하지 않는 유저일경우 로그인 창으로 이동
		}
		form.setLoggedInMember(loggedInMember);
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
		
		ProducterEntity saved = producterRepository.save(producterEntity);
		
		return "redirect:/main/" + saved.getId();
	}
	
	@GetMapping("/main/{id}")
	public String show(@PathVariable Long id, Model model, HttpSession session) {
		// main/{id}창을 관리자도 사용하기때문에 관리자, 일반유저, 판매자가 동일한 프론트에서 다르게 보이기 위해 권한값 부여해서 타임리프 사용
		boolean editAccess = false; //수정&삭제
		boolean isAdmin = false; //검토완료 버튼 보이게

		String loginId = (String) session.getAttribute("loginId");
		String producterId = producterService.show(id).getLoggedInMember().getMemberId();

		if (loginId == producterId){
			editAccess = true;
		}
		if(memberService.findByMemberId(loginId).getVerify() == 9){
			editAccess = true;
			isAdmin = true;
		}

		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);

		model.addAttribute("producter", producterEntity);
		model.addAttribute("editAccess", editAccess);
		model.addAttribute("isAdmin", isAdmin);

		return "producterShow";
	}
	
	@GetMapping("/main/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);

		model.addAttribute("producter", producterEntity);
		return "producterEdit";
	}
	
	@PostMapping("/main/update")
	public String update(ProducterDTO form, HttpSession session) {

		MemberEntity loggedInMember;
		try{
			loggedInMember = memberService.findByMemberId((String) session.getAttribute("loginId"));
		} catch(Exception e){
			return "login"; // 디비에 존재 하지 않는 유저일경우 로그인 창으로 이동
		}

		form.setLoggedInMember(loggedInMember);
		ProducterEntity producterEntity = form.toEntity(form);
		
		ProducterEntity target = producterRepository.findById(producterEntity.getId()).orElse(null);
		if(target != null) {
			producterRepository.save(producterEntity);
		}
		
		return "redirect:/main/" + producterEntity.getId();
	}
	
	@GetMapping("/main/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		
		ProducterEntity target = producterRepository.findById(id).orElse(null);
		
		if(target != null) {
			producterRepository.delete(target);
			rttr.addFlashAttribute("msg", "Deletion is complete");
		}
		
		return "redirect:/main";
	}

	@PatchMapping("/main/{id}") // 관리자 검토 버튼을 위한 함수
	public void adminGoOver(@PathVariable Long id){
		producterService.updateAdminGoOver(id);
	}
}

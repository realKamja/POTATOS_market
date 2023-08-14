package com.poteto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poteto.dto.ProducterDTO;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.ProducterRepository;

@Controller
public class ProducterController {
	
	@Autowired
	private ProducterRepository producterRepository;
	
	@GetMapping("/producterform")
	public String newArticleForm() {
		return "producterform";
	}
	
	@PostMapping("/producterform")
	public String createAricle(ProducterDTO form, @RequestParam("imageFile") MultipartFile imageFile) {
		
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
	public String show(@PathVariable Long id, Model model) {
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);
		
		model.addAttribute("producter", producterEntity);
		
		return "producterShow";
	}
	
	@GetMapping("/main/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		
		ProducterEntity producterEntity = producterRepository.findById(id).orElse(null);

		model.addAttribute("producter", producterEntity);
		return "producterEdit";
	}
	
	@PostMapping("/main/update")
	public String update(ProducterDTO form) {
		
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
}

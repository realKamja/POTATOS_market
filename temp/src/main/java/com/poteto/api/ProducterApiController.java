package com.poteto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poteto.dto.ProducterDTO;
import com.poteto.entity.ProducterEntity;
import com.poteto.sevice.ProducterService;

@RestController
public class ProducterApiController {

	@Autowired
	private ProducterService producterService;
	/*
	//GET
	@GetMapping("/api/producter")
	public List<ProducterEntity> index(){
		return producterService.index();
	}
	*/
	@GetMapping("/api/producter")
    public ResponseEntity<List<ProducterEntity>> index(){
        List<ProducterEntity> articles = producterService.index();
        return ResponseEntity.ok(articles);
    }
	
	@GetMapping("/api/producter/{id}")
	public ProducterEntity show(@PathVariable Long id){
		return producterService.show(id);
	}
	
	//POST
	@PostMapping("/api/producter")
	public ResponseEntity<ProducterEntity> create(@RequestBody ProducterDTO dto){
		ProducterEntity created = producterService.create(dto);
		
		if(created != null) {
			return ResponseEntity.status(HttpStatus.OK).body(created);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	//PATCH
	@PatchMapping("/api/producter/{id}")
	public ResponseEntity<ProducterEntity> update(@PathVariable Long id, @RequestBody ProducterDTO dto) {
		
		ProducterEntity updated = producterService.update(id, dto);
		
		if(updated != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	//DELETE
	@DeleteMapping("/api/producter/{id}")
	public ResponseEntity<ProducterEntity> delete(@PathVariable Long id) {
		
		ProducterEntity deleted = producterService.delete(id);
		
		if(deleted != null) {
			return ResponseEntity.status(HttpStatus.OK).body(deleted);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}

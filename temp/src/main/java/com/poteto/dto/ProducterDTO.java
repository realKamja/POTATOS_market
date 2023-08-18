package com.poteto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class ProducterDTO {
	
	private Long id;
	
	private String ProducterTitle;
	
	private String ProducterDetail;
	
	private String ProducterName;
	
	private String Category;
	
	private String ProducterPrice;
	
	private String ProducterAmount;
	
	private String ProducterBank;

	private String ProducterImagePath;
	
	private String ProducterImage;
	
	@JsonProperty("logged_in_member_id")
	private MemberEntity LoggedInMember;
	
	public ProducterEntity toEntity(ProducterDTO ProducterDTO) {
		
		ProducterEntity producterEntity = new ProducterEntity();
		
		producterEntity.setId(ProducterDTO.getId());
		producterEntity.setProducterTitle(ProducterDTO.getProducterTitle());
		producterEntity.setProducterDetail(ProducterDTO.getProducterDetail());
		producterEntity.setProducterName(ProducterDTO.getProducterName());
		producterEntity.setCategory(ProducterDTO.getCategory());
		producterEntity.setProducterPrice(ProducterDTO.getProducterPrice());
		producterEntity.setProducterAmount(ProducterDTO.getProducterAmount());
		producterEntity.setProducterBank(ProducterDTO.getProducterBank());
		producterEntity.setProducterImagePath(ProducterDTO.getProducterImagePath());
		producterEntity.setProducterImage(ProducterDTO.getProducterImage());
		producterEntity.setLoggedInMember(ProducterDTO.getLoggedInMember());
		
		return producterEntity;
	}
}

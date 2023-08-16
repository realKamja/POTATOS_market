package com.poteto.dto;

import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import lombok.*;

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

	private String ProducterAccount;
	
	private String ProducterBank;

	private MemberEntity loggedInMember;
	
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
		producterEntity.setProducterAccount(ProducterDTO.getProducterAccount());

		producterEntity.setLoggedInMember(ProducterDTO.getLoggedInMember());
		
		return producterEntity;
	}
}

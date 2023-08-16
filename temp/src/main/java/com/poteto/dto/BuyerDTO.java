package com.poteto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyerDTO {
	private Long id;
	private String BuyerAmount;
	private String BuyerBank;
	private String BuyerAccount;
	private String BuyerAddress;
	
	@JsonProperty("sale_title")
	private ProducterEntity SaleTitle;
	
	@JsonProperty("logged_in_member_id")
	private MemberEntity LoggedInMember;
	
	public BuyerEntity toEntity(BuyerDTO buyerDTO) {
		BuyerEntity buyerEntity = new BuyerEntity();
		
		buyerEntity.setId(buyerDTO.getId());
		buyerEntity.setBuyerAmount(buyerDTO.getBuyerAmount());
		buyerEntity.setBuyerBank(buyerDTO.getBuyerBank());
		buyerEntity.setBuyerAccount(buyerDTO.getBuyerAccount());
		buyerEntity.setBuyerAddress(buyerDTO.getBuyerAddress());
		buyerEntity.setSaleTitle(buyerDTO.getSaleTitle());
		buyerEntity.setLoggedInMember(buyerDTO.getLoggedInMember());
		
		return buyerEntity;
	}
}

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
	
	@JsonProperty("sale_id")
	private ProducterEntity SaleId;
	
	@JsonProperty("logged_in_member_id")
	private MemberEntity LoggedInMember;
	
	public static BuyerDTO toBuyerDTO(BuyerEntity buyerEntity) {
		BuyerDTO buyerDTO = new BuyerDTO();

		buyerDTO.setId(buyerEntity.getId());
		buyerDTO.setBuyerAmount(buyerEntity.getBuyerAmount());
		buyerDTO.setBuyerBank(buyerEntity.getBuyerBank());
		buyerDTO.setBuyerAccount(buyerEntity.getBuyerAccount());
		buyerDTO.setBuyerAddress(buyerEntity.getBuyerAddress());
		buyerDTO.setSaleId(buyerEntity.getSaleId());
		buyerDTO.setLoggedInMember(buyerEntity.getLoggedInMember());
		
		return buyerDTO;
	}
}

package com.poteto.dto;

import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;

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
	private String Buyeraddress;
	
	private MemberEntity loggedInMember;
	
	public static BuyerDTO toBuyerDTO(BuyerEntity buyerEntity) {
		BuyerDTO buyerDTO = new BuyerDTO();
		
		buyerDTO.setId(buyerEntity.getId());
		buyerDTO.setBuyerAmount(buyerEntity.getBuyerAmount());
		buyerDTO.setBuyerBank(buyerEntity.getBuyerBank());
		buyerDTO.setBuyerAccount(buyerEntity.getBuyerAccount());
		buyerDTO.setBuyeraddress(buyerEntity.getBuyeraddress());
		
		return buyerDTO;
	}
}

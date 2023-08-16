package com.poteto.dto;

import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;
import com.poteto.entity.ProducterEntity;
import lombok.*;

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
	private ProducterEntity product;
	
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

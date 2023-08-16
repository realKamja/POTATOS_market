package com.poteto.entity;

import com.poteto.dto.BuyerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buyer_table")
public class BuyerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column
	private String BuyerAmount;
	
	@Column
	private String BuyerBank;
	
	@Column
	private String BuyerAccount;
	
	@Column
	private String BuyerAddress;
	
	@ManyToOne // 다대일 관계 설정
	@JoinColumn(name = "sale_title") // 외래키 설정
	private ProducterEntity SaleTitle ; // 판매 게시판 제목
	
	@ManyToOne // 다대일 관계 설정
	@JoinColumn(name = "logged_in_member_id") // 외래키 설정
	private MemberEntity LoggedInMember; // 로그인된 사용자 아이디
	
	public BuyerDTO toDTO(BuyerEntity buyerEntity) {
		
		BuyerDTO buyerDTO = new BuyerDTO();
		
		buyerDTO.setId(buyerEntity.getId());
		buyerDTO.setBuyerAmount(buyerEntity.getBuyerAmount());
		buyerDTO.setBuyerBank(buyerEntity.getBuyerBank());
		buyerDTO.setBuyerAccount(buyerEntity.getBuyerAccount());
		buyerDTO.setBuyerAddress(buyerEntity.getBuyerAddress());
		buyerDTO.setSaleTitle(buyerEntity.getSaleTitle());
		buyerDTO.setLoggedInMember(buyerEntity.getLoggedInMember());
		
		return buyerDTO;
	}
}

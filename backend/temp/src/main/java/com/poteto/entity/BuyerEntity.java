package com.poteto.entity;

import com.poteto.dto.BuyerDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buyer_table")
@ToString
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
	private String Buyeraddress;
	
	@ManyToOne // 다대일 관계 설정
	@JoinColumn(name = "logged_in_member_id") // 외래키 설정
	private MemberEntity loggedInMember; // 로그인된 사용자 아이디

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProducterEntity product; //관리자가 거래내력 조회할 때 buyer테이블을 호출하기 때문에 판매자 정보와 해당 게시물을 불러오기 위해 필요
	
	public static BuyerEntity toBuyerEntity(BuyerDTO buyerDTO) {
		BuyerEntity buyerEntity = new BuyerEntity();

		//buyerEntity.setId(buyerDTO.getId());
		//setId를 dto로 해주면 디비에 buy_id 값이 이미 존재하는 걸로 인지하여(id!=null)
		//repository.save를 update기능으로 디비에 저장하게 됩니다. setId는 지울게요!
		buyerEntity.setBuyerAmount(buyerDTO.getBuyerAmount());
		buyerEntity.setBuyerBank(buyerDTO.getBuyerBank());
		buyerEntity.setBuyerAccount(buyerDTO.getBuyerAccount());
		buyerEntity.setBuyeraddress(buyerDTO.getBuyeraddress());
		buyerEntity.setLoggedInMember(buyerDTO.getLoggedInMember());
		buyerEntity.setProduct(buyerDTO.getProduct());
		
		return buyerEntity;
	}
}

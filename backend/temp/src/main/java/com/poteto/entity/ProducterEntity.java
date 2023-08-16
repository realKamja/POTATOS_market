package com.poteto.entity;

import com.poteto.dto.ProducterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "producter_table")
public class ProducterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ProducterTitle;
	
	@Column
	private String ProducterDetail;
	
	@Column
	private String ProducterName;
	
	@Column
	private String Category;
	
	@Column
	private String ProducterPrice;
	
	@Column
	private String ProducterAmount;
	
	@Column
	private String ProducterBank;

	@Column
	private String ProducterAccount;
	
	@Column
	private String producterImage;  // 이미지 파일명을 저장하는 필드

	@ColumnDefault("false")
	private boolean adminGoOver; // 관리자 검토 여부

	@ManyToOne // 다대일 관계 설정
	@JoinColumn(name = "logged_in_member_id") // 외래키 설정
	private MemberEntity loggedInMember; // 로그인된 사용자 아이디
	
	public ProducterDTO toDTO(ProducterEntity producterEntity) {
		
		ProducterDTO producterDTO = new ProducterDTO();

		producterDTO.setId(producterEntity.getId());
		producterDTO.setProducterTitle(producterEntity.getProducterTitle());
		producterDTO.setProducterDetail(producterEntity.getProducterDetail());
		producterDTO.setProducterName(producterEntity.getProducterName());
		producterDTO.setCategory(producterEntity.getCategory());
		producterDTO.setProducterPrice(producterEntity.getProducterPrice());
		producterDTO.setProducterAmount(producterEntity.getProducterAmount());
		producterDTO.setProducterBank(producterEntity.getProducterBank());
		producterDTO.setProducterAccount(producterEntity.getProducterAccount());
		
		return producterDTO;
	}
	
	public void patch(ProducterEntity producterEntity) {
		if(producterEntity.ProducterTitle != null) {
			this.ProducterTitle = producterEntity.ProducterTitle;
		}
		if(producterEntity.ProducterDetail != null) {
			this.ProducterDetail = producterEntity.ProducterDetail;
		}
		if(producterEntity.ProducterName != null) {
			this.ProducterName = producterEntity.ProducterName;
		}
		if(producterEntity.Category != null) {
			this.Category = producterEntity.Category;
		}
		if(producterEntity.ProducterPrice != null) {
			this.ProducterPrice = producterEntity.ProducterPrice;
		}
		if(producterEntity.ProducterAmount != null) {
			this.ProducterAmount = producterEntity.ProducterAmount;
		}
		if(producterEntity.ProducterBank != null) {
			this.ProducterBank = producterEntity.ProducterBank;
		}
		if(producterEntity.ProducterAccount != null) {
			this.ProducterAccount = producterEntity.ProducterAccount;
		}
	}
}

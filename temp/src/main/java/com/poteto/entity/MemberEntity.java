package com.poteto.entity;

import com.poteto.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_table")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column (unique = true) // unique 제약조건 추가
	private String memberId;
	
	@Column
	private String memberPassword;
	
	@Column
	private String memberName;
	
	@Column
	private String memberPhone;
	
	@Column
	private String memberEmail;

	@ColumnDefault("0")
	private int verify;
	
	public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		
		memberEntity.setId(memberDTO.getId());
		memberEntity.setMemberId(memberDTO.getMemberId());
		memberEntity.setMemberPassword(memberDTO.getMemberPassword());
		memberEntity.setMemberName(memberDTO.getMemberName());
		memberEntity.setMemberPhone(memberDTO.getMemberPhone());
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		
		return memberEntity;
	}
}

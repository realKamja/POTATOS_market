package com.poteto.dto;

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
public class MemberDTO {
	private long id;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private int verify;
	
	public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId(memberEntity.getId());
		memberDTO.setMemberId(memberEntity.getMemberId());
		memberDTO.setMemberPassword(memberEntity.getMemberPassword());
		memberDTO.setMemberName(memberEntity.getMemberName());
		memberDTO.setMemberPhone(memberEntity.getMemberPhone());
		memberDTO.setMemberEmail(memberEntity.getMemberEmail());
		memberDTO.setVerify(memberDTO.getVerify());

		return memberDTO;
	}
}

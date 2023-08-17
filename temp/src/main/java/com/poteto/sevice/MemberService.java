package com.poteto.sevice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poteto.dto.MemberForAdminDTO;
import org.springframework.stereotype.Service;

import com.poteto.dto.MemberDTO;
import com.poteto.entity.MemberEntity;
import com.poteto.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberDTO login(MemberDTO memberDTO) {
		// 1. 회원이 입력한 이메일로 DB에서 조회를 함
		// 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치한지 판단
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
		if(byMemberId.isPresent()) {
			// 조회 결과가 있다(해당 아이디를 가진 회원 정보가 있다)
			MemberEntity memberEntity = byMemberId.get(); // get을 통해 byMemberEntity에 씌워진 Optinal을 벗겨냄
			if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				// 비밀번호가 일치
				// entity -> dto 변환 후 리턴
				MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
				return dto;
			}
			else {
				// 비밀번호 불일치(로그인 실패)
				return null;
			}

		}
		else {
			// 조회 결과가 없다(해당 아이디을 가진 회원이 없다)
			return null;
		}
	}

	public void signin(MemberDTO memberDTO) {
		// 1. dto -> entity 변환
		// 2. repository의 save 메서드 호출
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
		memberRepository.save(memberEntity);
		// repository의 save메서드 호출 (조건 entity객체를 넘겨줘야 함)
	}

	public String IdCheck(String memberId) {
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
		if(byMemberId.isPresent()) {
			// 조회결과가 있다 -> 중복된 memberEmail이다
			return null;
		}
		else {
			// 조회결과가 있다 -> 사용할 수 있다
			return "ok";
		}
	}

	public MemberEntity findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId).get();
	}

	public boolean isAdmin(String loginId){
		if(findByMemberId(loginId).getVerify() == 9){ //verify가 9이면 관리자이다
			return true; //관리자이면 true반환
		}
		else{
			return false;
		}
	}

	public List<MemberForAdminDTO> membersForAdmin(){
		List<MemberEntity> entityList = memberRepository.findAll();
		List<MemberForAdminDTO> memberForAdmindDtoList = entityList.stream().map(memberEntity -> new MemberForAdminDTO(memberEntity)).collect(Collectors.toList());
		return memberForAdmindDtoList;
	}
}
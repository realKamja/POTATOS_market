package com.poteto.sevice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poteto.dto.BuyerDTO;
import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;
import com.poteto.repository.BuyerRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerService {
	
	private final BuyerRepository buyerRepository;
	private final MemberService memberService;
	
	public void buyInformationSave(BuyerDTO buyerDTO, HttpSession session) {
		// 1. dto -> entity 변환
	    // 2. repository의 save 메서드 호출
	    BuyerEntity buyerEntity = buyerDTO.toEntity(buyerDTO);

	    // 로그인한 멤버 정보 가져오기
	    String loggedInUsername = (String) session.getAttribute("loginId");
	    MemberEntity loggedInMember = memberService.findByMemberId(loggedInUsername);

	    buyerEntity.setLoggedInMember(loggedInMember);  // 올바른 멤버 정보 설정

	    // repository의 save메서드 호출 (조건 entity객체를 넘겨줘야 함)
	    buyerRepository.save(buyerEntity);
	}
	
	public List<BuyerEntity> allBuyerList(){
		return buyerRepository.findAll();
	}
}

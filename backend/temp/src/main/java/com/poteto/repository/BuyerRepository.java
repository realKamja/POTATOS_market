package com.poteto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poteto.entity.BuyerEntity;
import com.poteto.entity.MemberEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Long>{
	List<BuyerEntity> findByLoggedInMember(MemberEntity loggedInMember);
}

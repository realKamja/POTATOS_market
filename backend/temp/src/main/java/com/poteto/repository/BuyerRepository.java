package com.poteto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poteto.entity.BuyerEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Long>{
	
}

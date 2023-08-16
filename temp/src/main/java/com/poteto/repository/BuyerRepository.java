package com.poteto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poteto.entity.BuyerEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Long>{
	@Query("SELECT p.SaleTitle.ProducterTitle FROM BuyerEntity p WHERE p.LoggedInMember.id = :id")
    List<String> findSaleId(@Param("id") Long id);
}

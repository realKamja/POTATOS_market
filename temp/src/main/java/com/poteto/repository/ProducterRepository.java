package com.poteto.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.poteto.entity.ProducterEntity;


public interface ProducterRepository extends CrudRepository<ProducterEntity, Long>{
	@Override
	ArrayList<ProducterEntity> findAll();
	
	@Query("SELECT p.ProducterTitle FROM ProducterEntity p WHERE p.LoggedInMember.id = :id")
    List<String> findSaleItemTitle(@Param("id") Long id);
}

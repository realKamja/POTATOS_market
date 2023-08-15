package com.poteto.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.poteto.entity.ProducterEntity;


public interface ProducterRepository extends CrudRepository<ProducterEntity, Long>{
	@Override
	ArrayList<ProducterEntity> findAll();

}

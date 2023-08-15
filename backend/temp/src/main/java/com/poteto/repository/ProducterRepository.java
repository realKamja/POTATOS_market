package com.poteto.repository;

import com.poteto.entity.ProducterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface ProducterRepository extends CrudRepository<ProducterEntity, Long>{
	@Override
	ArrayList<ProducterEntity> findAll();

}

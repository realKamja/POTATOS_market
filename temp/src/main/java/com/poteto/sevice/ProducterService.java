package com.poteto.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poteto.dto.ProducterDTO;
import com.poteto.entity.ProducterEntity;
import com.poteto.repository.ProducterRepository;

import jakarta.transaction.Transactional;

@Service
public class ProducterService {
	@Autowired
	private ProducterRepository producterRepository;
	
	public List<ProducterEntity> index(){
		
		return producterRepository.findAll();
	}

	public ProducterEntity show(Long id) {
		
		return producterRepository.findById(id).orElse(null);
	}
	
	public ProducterEntity create(ProducterDTO dto) {
		
		ProducterEntity producterEntity = dto.toEntity(dto);
		if(producterEntity.getId() == null) {
			return null;
		}
		
		return producterRepository.save(producterEntity);
	}
	
	public ProducterEntity update(Long id, ProducterDTO dto) {
		
		ProducterEntity producterEntity = dto.toEntity(dto);
		
		ProducterEntity target = producterRepository.findById(id).orElse(null);
		
		if(target==null || id!=producterEntity.getId()) {
			return null;
		}
		
		target.patch(producterEntity);
		ProducterEntity updated = producterRepository.save(target);
		
		return updated;
	}
	
	public ProducterEntity delete(Long id) {
		ProducterEntity target = producterRepository.findById(id).orElse(null);
		
		if(target == null) {
			return null;
		}
		
		producterRepository.delete(target);
		return target;
	}
	
	@Transactional
	public List<ProducterEntity> createArticles(List<ProducterDTO> dtos){
		// dto 묶음을 entity 묶음으로 변환
		List<ProducterEntity> articleList = dtos.stream()
				.map(dto -> dto.toEntity(dto))
				.collect(Collectors.toList());
		/*
		 * List<Article> articleList = new ArrayList<>();
		 * for(int i=0; i<dtso.size(); i++){
		 * 		ArticleForm dto = dtos.get(i);
		 * 		Article entity = dto.toEntity();
		 * 		articleList.add(entity);
		 * }
		 */
		
		// entity 묶음을 DB로 저장
		articleList.stream()
				.forEach(article -> producterRepository.save(article));
		/*
		 * for(int i=0; i<articleList.size(); i++){
		 * 		Article article = articleList.get(i);
		 * 		articleRepository.save(article);
		 */
		
		// 강제 예외 발생
		producterRepository.findById(-1L).orElseThrow(
				() -> new IllegalArgumentException("failure")
		);
		
		
		// 결과값 반환
		return articleList;
	}
	
	@Transactional
    public void updateAdminGoOver(Long id) {
        ProducterEntity producter = show(id);
        if (producter != null) {
            producter.setAdminGoOver(true);
            producterRepository.save(producter);
        } else {
            throw new IllegalArgumentException("Producter not found");
        }
    }
}

package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.respositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.ResoucerNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly=true)
	public CategoryDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResoucerNotFoundException("Entity not found"));
		return new CategoryDTO(obj.get());
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(id); 
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		}
		catch(javax.persistence.EntityNotFoundException e) {
			throw new ResoucerNotFoundException("ID not found "  + id);
		}
	}
}

package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repository.CategoryRepo;
import com.blog.userService.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepo categoryRepo;

	// ------------CREATE OR SAVE--------------------------------
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = new Category();
		BeanUtils.copyProperties(categoryDto, cat);

		Category catSave = categoryRepo.save(cat);

		CategoryDto catDto = new CategoryDto();

		BeanUtils.copyProperties(catSave, catDto);

		return catDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		
	        Optional<Category> cat =categoryRepo.findById(categoryId);
	        if(!cat.isPresent()) throw new ResourceNotFoundException("category", "categoryId", categoryId);
	        
	        Category cat1=cat.get();
	        cat1.setCategoryDescription(categoryDto.getCategoryDescription());
	        cat1.setCategoryTitle(categoryDto.getCategoryTitle());
	        
	      Category updatedCat=categoryRepo.save(cat1);
	      CategoryDto catDto=new CategoryDto();
	      BeanUtils.copyProperties(updatedCat, catDto);
		return catDto;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Optional<Category> cat=categoryRepo.findById(categoryId);
		if(!cat.isPresent()) throw new ResourceNotFoundException("category", "id", categoryId);
		categoryRepo.delete(cat.get());
	}

	@Override
	public CategoryDto getCategory(Long categoryId) {
		Optional<Category> cat=categoryRepo.findById(categoryId);
		if(!cat.isPresent()) throw new ResourceNotFoundException("category", "id", categoryId);
		Category cat1=cat.get();
		CategoryDto catDto=new CategoryDto();
		catDto.setCategoryId(cat1.getCategoryId());
		catDto.setCategoryDescription(cat1.getCategoryDescription());
		catDto.setCategoryTitle(cat1.getCategoryTitle());
		return catDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
	     List<Category>catList=categoryRepo.findAll();
	     List<CategoryDto>dtoList=new ArrayList<>();
	     
	     for(Category cat:catList) {
	    	 CategoryDto dto=new CategoryDto();
	    	 BeanUtils.copyProperties(cat, dto);
	    	 dtoList.add(dto);
	     }
	     
		return dtoList;
	}

}

package com.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
//import com.blog.repository.CategoryRepo;
import com.blog.userService.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@Tag(name = "Categories APIs", description = "Endpoints for user's categories")

public class CategoryController {
	
private CategoryService categoryService;


@PostMapping("/create/category")
ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cat) {
	
	CategoryDto catDto=categoryService.createCategory(cat);
	
	return new ResponseEntity<>(catDto,HttpStatus.CREATED);
	
}

@PutMapping("/category/update/{id}")
ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cat,@PathVariable Long id) {
	
	CategoryDto catDto=categoryService.updateCategory(cat, id);
	
	return new ResponseEntity<>(catDto,HttpStatus.OK);
	
}

@DeleteMapping("/category/deleteBy{id}")
ResponseEntity<ApiResponse>deleteCategory(@PathVariable Long id){
	categoryService.deleteCategory(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("category is successfully deleted", true),HttpStatus.OK);
}

@GetMapping("/category/getBy/{id}")
ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
	
	CategoryDto catDto=categoryService.getCategory(id);
	
	return new ResponseEntity<>(catDto,HttpStatus.OK);
	
}

@GetMapping("/category/getAll")
ResponseEntity<List<CategoryDto>> getAllCategories() {
	
	List<CategoryDto> catDto=categoryService.getAllCategories();
	
	return new ResponseEntity<>(catDto,HttpStatus.OK);
	
}

}

package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	
private Long categoryId;
@NotBlank
@Size(min=5,message="title must be greater than 5 chars!!")
private String categoryTitle;

@NotBlank
@Size(min=10,message="description must be conatin 10 chars")
private String categoryDescription;

}

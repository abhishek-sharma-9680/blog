package com.blog.payloads;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.Data;

@Data

public class PostDto {

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Long categoryId;
	
	private Long userId;
	
}

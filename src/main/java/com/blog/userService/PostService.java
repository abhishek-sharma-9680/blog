package com.blog.userService;

import java.util.List;

import com.blog.payloads.PostDto;
import com.blog.responses.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto);

	// update
	PostDto updatePost(PostDto postDto, Long postId);

	// delete
	void deletePost(Long postId);

	// get all post
	List<PostResponse> getAllPost();

	// get single post
	PostResponse getPostById(Long postId);

	// get all post by category
	List<PostResponse> getPostsByCategory(Long categoryId);

	// get All by User
	List<PostResponse> getPostsByUser(Long userId);

	// search posts
	List<PostDto> searchPosts(String keyword);
}

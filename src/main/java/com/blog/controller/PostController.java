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

import com.blog.payloads.PostDto;
import com.blog.responses.PostResponse;
import com.blog.userService.PostService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostController {
	private PostService postService;

//========================================CREATE POST=====================================================================
	@PostMapping("/create/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostDto postDto1 = postService.createPost(postDto);

		return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
	}

//==============================================GET POST BY USER===============================================================
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable Long userId) {
		List<PostResponse> postResList = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostResponse>>(postResList, HttpStatus.CREATED);
	}

//================================================GET POST BY CATEGORY========================================================
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostResponse>> getPostsByCategory(@PathVariable Long categoryId) {
		List<PostResponse> postResList = postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostResponse>>(postResList, HttpStatus.CREATED);
	}
	
//================================================GET POST BY ID==================================================================
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostResponse> getPostsByPostId(@PathVariable Long postId) {
		PostResponse postRes = postService.getPostById(postId);

		return new ResponseEntity<PostResponse>(postRes, HttpStatus.CREATED);
	}
	
//===============================================GET ALL POST=================================================================
	@GetMapping("/getAll/post")
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		List<PostResponse> postRes = postService.getAllPost();

		return new ResponseEntity<List<PostResponse>>(postRes, HttpStatus.CREATED);
	}
	
//===============================================DELETE POST BY ID=========================================================
	@DeleteMapping("/delete/postBy/{id}")
	public ResponseEntity<String>deletePosts(@PathVariable Long id) {
		        postService.deletePost(id);

		return new ResponseEntity<String>("post deleted successfully", HttpStatus.OK);
	}
	
//===============================================UPDATE POST BY ID===================================================================
	@PutMapping("/update/post/{id}")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
              PostDto postRes=postService.updatePost(postDto, id);

       return new ResponseEntity<PostDto>(postRes, HttpStatus.OK);
}
	
	
}

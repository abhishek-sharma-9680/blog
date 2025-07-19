package com.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.payloads.PostDto;
import com.blog.responses.PostPageResponse;
import com.blog.responses.PostResponse;
import com.blog.userService.FileService;
import com.blog.userService.PostService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

public class PostController {
	private PostService postService;
	

//	private FileService fileService;
//    
//	@Value("${project.image}")
//	private String path;
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
	@GetMapping("/getAll/post{pageNumber}/{pageSize}/{sortBy}")
	public ResponseEntity<PostPageResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postId",required=false) String sortBy) {
		PostPageResponse postRes = postService.getAllPost(pageNumber,pageSize,sortBy);

		return new ResponseEntity<PostPageResponse>(postRes, HttpStatus.CREATED);
	}
	
//===============================================DELETE POST BY ID=========================================================
	@DeleteMapping("/delete/postBy/{id}")
	public ResponseEntity<String>deletePosts(@PathVariable Long id) {
		        postService.deletePost(id);

		return new ResponseEntity<String>("post deleted successfully", HttpStatus.OK);
	}
	
//===============================================UPDATE POST BY ID===================================================================
	@PutMapping("/update/post/{id}")
	public ResponseEntity<PostResponse>updatePost(@RequestBody PostResponse postDto, @PathVariable Long id) {
		PostResponse postRes=postService.updatePost(postDto, id);

       return new ResponseEntity<PostResponse>(postRes, HttpStatus.OK);
}
//=====================================================SEARCH BY TITLE KEYWORD=====================================================	
	@GetMapping("/getpost/by/title/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@RequestParam(value="keyword") String keyWord) {
		List<PostDto> postDto = postService.searchPosts(keyWord);

		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.CREATED);
	}
//==================================================== POST IMAGE UPLOAD ===================================================
//	
//	@PostMapping("/post/image/upload/{postId}")
//	public ResponseEntity<PostResponse> uploadPostImage(@RequestParam("image") MultipartFile image,
//			                                       @PathVariable Long postId) throws IOException {
//		 PostResponse postDto=postService.getPostById(postId);
//       String fileName=fileService.uploadImage(path, image);
//      
//       postDto.setImageName(fileName);
//       PostResponse updatePost=postService.updatePost(postDto, postId);
//		
//		return new ResponseEntity<PostResponse>(updatePost, HttpStatus.OK);
//	}

	
}

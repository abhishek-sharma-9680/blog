package com.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentsDto;
import com.blog.userService.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Tag(name = "Comments APIs", description = "Endpoints for user's comments")

public class CommentController {

	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto comDto,@PathVariable Long postId){
		
		CommentsDto dto=commentService.createComments(comDto, postId);
		
		return new ResponseEntity<CommentsDto>(dto,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{commentsId}/comment")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentsId){
		
		commentService.delteComment(commentsId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment delted successfully !",true),HttpStatus.OK);
		
	}
}

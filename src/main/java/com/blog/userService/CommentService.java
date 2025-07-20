package com.blog.userService;

import com.blog.payloads.CommentsDto;

public interface CommentService {

	CommentsDto createComments(CommentsDto dto,Long postId);
	
	void delteComment(Long commentId);
	
}

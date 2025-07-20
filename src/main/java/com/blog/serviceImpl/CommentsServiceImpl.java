package com.blog.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.blog.entities.Comments;
import com.blog.entities.Post;
import com.blog.payloads.CommentsDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.userService.CommentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentsServiceImpl implements CommentService  {

	private PostRepository postRepo;
	
	private CommentRepository commentRepo;

	@Override
	public CommentsDto createComments(CommentsDto dto, Long postId) {
		
		Post post = postRepo.findById(postId)
			    .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

		
		Comments comment=new Comments();
		BeanUtils.copyProperties(dto, comment);
		comment.setPost(post);
		Comments com=commentRepo.save(comment);
		CommentsDto comDto=new CommentsDto();
		BeanUtils.copyProperties(comment,comDto);
		return comDto;
	}

	@Override
	public void delteComment(Long commentId) {
		Comments comment = commentRepo.findById(commentId)
			    .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));

		commentRepo.delete(comment);
		
	}

}

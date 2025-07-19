package com.blog.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.UserDto;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepo;
import com.blog.responses.PostPageResponse;
import com.blog.responses.PostResponse;
import com.blog.userService.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private PostRepository postRepo;

	private UserRepo userRepo;

	private CategoryRepo categoryRepo;

//===========================================================================================================================	
	@Override
	public PostDto createPost(PostDto postDto) throws ResourceNotFoundException, BeansException {
//		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
//
//		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));

		Post post = new Post();

		Category category = new Category();
		category.setCategoryId(postDto.getCategoryId());

		User user = new User();
		user.setId(postDto.getUserId());

		post.setCategory(category);
		post.setUser(user);

		// BeanUtils.copyProperties(postDto, post);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());

		Post savePost = postRepo.save(post);
		PostDto postDto1 = new PostDto();

		// postDto1.setCategory(savePost.getCategory());
		BeanUtils.copyProperties(savePost, postDto1);
		postDto1.setCategoryId(postDto.getCategoryId());
		postDto1.setUserId(postDto.getUserId());
		return postDto1;
	}

//=====================================--------------------------------=====================================================	

	@Override
	public PostResponse updatePost(PostResponse postDto, Long postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		post.setContent(postDto.getContent());
		post.setDate(new Date());
		post.setTitle(postDto.getTitle());
		Post updated=postRepo.save(post);
		PostResponse postRes=new PostResponse();
		BeanUtils.copyProperties(updated, postRes);
		
		return postRes;
	}

//=================================================================------------------------------------------=======================
	@Override
	public void deletePost(Long postId) {
	
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
         postRepo.delete(post);
	}

//=========================================--------------------------------------------------===================================	

	@Override
	public PostPageResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		
	Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		Page<Post>pagePost = postRepo.findAll(p);
		List<Post> postList=pagePost.getContent();
		
		List<PostResponse> resList = new ArrayList<>();
		for (Post obj : postList) {
			PostResponse response = new PostResponse();
			BeanUtils.copyProperties(obj, response);

			CategoryDto cat = new CategoryDto();
			cat.setCategoryDescription(obj.getCategory().getCategoryDescription());
			cat.setCategoryId(obj.getCategory().getCategoryId());
			cat.setCategoryTitle(obj.getCategory().getCategoryTitle());

			response.setCategory(cat);

			UserDto user = new UserDto();
			user.setId(obj.getUser().getId());
			user.setName(obj.getUser().getName());
			user.setAbout(obj.getUser().getAbout());
			user.setEmail(obj.getUser().getEmail());
			user.setPassword(obj.getUser().getPassword());

			response.setUser(user);

			resList.add(response);
		}
		PostPageResponse pageRes=new PostPageResponse();
		pageRes.setContent(resList);
		pageRes.setPageNumber(pagePost.getNumber());
		pageRes.setPageSize(pagePost.getSize());
		pageRes.setTotalElements(pagePost.getTotalElements());
		pageRes.setTotalPages(pagePost.getTotalPages());
		pageRes.setLastPage(pagePost.isLast());
		
		return pageRes;
		
		
	}

//------------------------+++++++++++++++++++++++++++++++++=======================================-----------------------++++++++++	

	@Override
	public PostResponse getPostById(Long postId) {
		Post obj = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		PostResponse response = new PostResponse();
		BeanUtils.copyProperties(obj, response);

		CategoryDto cat = new CategoryDto();
		cat.setCategoryDescription(obj.getCategory().getCategoryDescription());
		cat.setCategoryId(obj.getCategory().getCategoryId());
		cat.setCategoryTitle(obj.getCategory().getCategoryTitle());

		response.setCategory(cat);

		UserDto user = new UserDto();
		user.setId(obj.getUser().getId());
		user.setName(obj.getUser().getName());
		user.setAbout(obj.getUser().getAbout());
		user.setEmail(obj.getUser().getEmail());
		user.setPassword(obj.getUser().getPassword());

		response.setUser(user);

		return response;
	}

//================================================================================================================================	

	@Override
	public List<PostResponse> getPostsByCategory(Long categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		List<Post> postList = postRepo.findByCategory(category);
		List<PostResponse> dtoList = new ArrayList<>();
		for (Post obj : postList) {
			PostResponse postRes = new PostResponse();
			BeanUtils.copyProperties(obj, postRes);

			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryId(obj.getCategory().getCategoryId());
			categoryDto.setCategoryTitle(obj.getCategory().getCategoryTitle());
			categoryDto.setCategoryDescription(obj.getCategory().getCategoryDescription());
			postRes.setCategory(categoryDto);

			// Convert User
			UserDto userDto = new UserDto();
			userDto.setId(obj.getUser().getId());
			userDto.setName(obj.getUser().getName());
			userDto.setEmail(obj.getUser().getEmail());
			postRes.setUser(userDto);

			dtoList.add(postRes);
		}

		return dtoList;
	}

//================================================================================================================================	

	@Override
	public List<PostResponse> getPostsByUser(Long userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
		List<Post> postList = postRepo.findByUser(user);
		List<PostResponse> resList = new ArrayList<>();
		for (Post obj : postList) {
			PostResponse postRes = new PostResponse();
			BeanUtils.copyProperties(obj, postRes);

			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryId(obj.getCategory().getCategoryId());
			categoryDto.setCategoryTitle(obj.getCategory().getCategoryTitle());
			categoryDto.setCategoryDescription(obj.getCategory().getCategoryDescription());
			postRes.setCategory(categoryDto);

			// Convert User
			UserDto userDto = new UserDto();
			userDto.setId(obj.getUser().getId());
			userDto.setName(obj.getUser().getName());
			userDto.setEmail(obj.getUser().getEmail());
			postRes.setUser(userDto);

			resList.add(postRes);
		}

		return resList;
	}

//=============================================================================================================================	

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post>list=postRepo.searchByKeyword(keyword);
		
		List<PostDto>dtoList=new ArrayList<>();
		for(Post post:list) {
			PostDto obj=new PostDto();
			BeanUtils.copyProperties(post, obj);
			dtoList.add(obj);
		}
		return dtoList;
	}

}

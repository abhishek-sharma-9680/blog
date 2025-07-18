package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository extends JpaRepository<Post,Long>{

	 List<Post>findByUser(User user);
	 List<Post> findByCategory(Category category);
	    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyWord%")
	    List<Post> searchByKeyword(@Param("keyWord") String keyWord);
}

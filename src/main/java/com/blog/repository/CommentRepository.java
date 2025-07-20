package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}

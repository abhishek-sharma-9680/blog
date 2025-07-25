package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

}

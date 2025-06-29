package com.blog.responses;

import java.util.Date;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {

    private Long postId;

    private String title;

    private String content;

    private String imageName;

    private Date date;

    private CategoryDto category;

    private UserDto user;
}

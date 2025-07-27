package com.blog.responses;

import java.time.LocalDateTime;
import java.util.Date;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private Long postId;

    private String title;

    private String content;

    private String imageName;

    private LocalDateTime date;

    private CategoryDto category;

    private UserDto user;
}

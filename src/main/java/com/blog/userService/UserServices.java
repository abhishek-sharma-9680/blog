package com.blog.userService;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserServices  {
 public UserDto createUser(UserDto user);
 public UserDto updateUser(UserDto user,Long userId);
 public UserDto getUserById(Long userId);
 public List<UserDto>getAllUsers();
 public void deleteUser(Long userId);

}

package com.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.UserDto;
import com.blog.userService.UserServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class UserController {
	private UserServices userService;
	
@PostMapping("/create/user")
public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto user) {
	UserDto udto=userService.createUser(user);
	return new ResponseEntity<>(udto,HttpStatus.CREATED);
}
@GetMapping("/get/userById/{id}")
public ResponseEntity<UserDto> getById(@PathVariable Long id) {
	UserDto userDto=userService.getUserById(id);
    return ResponseEntity.ok(userDto);
}
@GetMapping("/get/allUsers")
public ResponseEntity<List<UserDto>>getAllUsers(){
	List<UserDto>dto=userService.getAllUsers();
	return ResponseEntity.ok(dto);
}

@PutMapping("update/user/by/{id}")
public ResponseEntity<UserDto>updateById(@RequestBody UserDto userdto,@PathVariable Long id){
	
	UserDto updatedUser=userService.updateUser(userdto, id);
	return new ResponseEntity<>(updatedUser,HttpStatus.OK);
}

@DeleteMapping("/delete/by/{id}")
public ResponseEntity<String> deletUser(@PathVariable Long id){
	userService.deleteUser(id);
	return  ResponseEntity.ok("user deleted successfully");
}
}

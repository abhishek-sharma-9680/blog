 package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repository.UserRepo;
import com.blog.userService.UserServices;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserServices {

	private UserRepo userRepo;
	//save method
	@Override
	public UserDto createUser(UserDto user) {
		User user1=new User();
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setAbout(user.getAbout());
		user1.setPassword(user.getPassword());
		userRepo.save(user1);
		user.setId(user1.getId());
		user.setName(user1.getName());
		user.setEmail(user1.getEmail());
		user.setAbout(user1.getAbout());
		user.setPassword(user1.getPassword());		
		
		return user;
	}
//UPDATE USER
	@Override
	public UserDto updateUser(UserDto userdto, Long userId) {
	        User user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	        user.setAbout(userdto.getAbout());
	        user.setEmail(userdto.getEmail());
	        user.setName(userdto.getName());
	        user.setPassword(userdto.getPassword());
	        User updatedUser=  userRepo.save(user);
	       
	         
	        userdto.setId(updatedUser.getId());
			userdto.setName(updatedUser.getName());
			userdto.setEmail(updatedUser.getEmail());
			userdto.setAbout(updatedUser.getAbout());
			userdto.setPassword(updatedUser.getPassword());		
			
	               
		return userdto;
	}
//GET USER
	@Override
	public UserDto getUserById(Long userId) {
		User user=userRepo.getReferenceById(userId);
		UserDto userDto=new UserDto();
		userDto.setId(userId);
		userDto.setName(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
	List<User>user=userRepo.findAll();
	List<UserDto>userDtoList=new ArrayList<>();
	for(User u:user) {
		UserDto userDto=new UserDto(u.getId(),u.getName(),u.getEmail(),u.getAbout(),u.getPassword());
		userDtoList.add(userDto);
	}
		return userDtoList;
	}
	
	//DELETE USER

	@Override
	public void deleteUser(Long userId) {
	User user=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));;
	
	userRepo.delete(user);
		
	}

}

package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
	
private Long id;
@NotEmpty
@Size(min=5,message="name must be min 5 characters !!")
private String name;
@Email(message="email address is not valid !!")
private String email;
@NotEmpty
 private String about;
@NotEmpty
@Size(min=3,max=15,message="passward must be min 3 chars and max 15 chars")
 private String password;
 
}

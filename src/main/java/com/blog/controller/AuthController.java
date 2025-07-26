package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.JwtAuthRequest;
import com.blog.payloads.JwtAuthResponse;
import com.blog.payloads.UserDto;
import com.blog.security.CustomUserDetailsService;
import com.blog.security.JwtTokenHelper;
import com.blog.userService.UserServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth/") // Better to be consistent with `/api/**` pattern
@Tag(name = "Authentication APIs", description = "Endpoints for user registration and login")

public class AuthController {

     @Autowired
	private UserServices userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest loginRequest) {
       
            // Authenticate user
            authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
      
        
         
        // Load user and generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
        String token = jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
    
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user and returns the registered user details",
            responses = {
                @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            }
        )
    
    @PostMapping("/register")
    public ResponseEntity<UserDto>register(@Valid @RequestBody UserDto userDto){
    	UserDto result=userService.registerNewUser(userDto);
    	  // You can throw a custom exception OR return a proper error response
    	if(result==null) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null); // or you can return a custom error object instead of null
    }
    	return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    
}

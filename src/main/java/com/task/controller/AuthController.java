package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.LoginDTO;
import com.task.dto.UserDTO;
import com.task.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto)
	{
		try {
			UserDTO dto=service.createUser(userDto);
			return new ResponseEntity<>(dto,HttpStatus.CREATED);
		} catch (Exception e) {
			
			throw new UsernameNotFoundException("Exception Accured",e);
		}
		
	}
	
	@PostMapping("/userlogin")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO)
	{
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
				);
		
		return new ResponseEntity<>("User Login Successfully",HttpStatus.OK);
		
	}
}

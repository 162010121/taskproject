package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.JwtAuthResponce;
import com.task.dto.LoginDTO;
import com.task.dto.UserDTO;
import com.task.service.UserService;
import com.task.serviceimpl.JwtService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
		try {
			UserDTO dto = service.createUser(userDto);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		} catch (Exception e) {

			throw new UsernameNotFoundException("Exception Accured", e);
		}

	}

	@PostMapping("/userlogin")
	public ResponseEntity<JwtAuthResponce> loginUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token=jwtService.generateToken(loginDTO.getEmail(), loginDTO.getPassword());

		return ResponseEntity.ok(new JwtAuthResponce(token));

	}
//	
//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody LoginDTO loginDTO)
//	{
//		
//		Authentication  authentication =authenticationManager.
//				authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
//		if(authentication.isAuthenticated())
//		{
//			return jwtService.generateToken(loginDTO.getEmail(),loginDTO.getPassword());
//
//		}
//		else
//		{
//			throw  new UsernameNotFoundException("Invalid Token");
//		}
//		
//		
//	}
}

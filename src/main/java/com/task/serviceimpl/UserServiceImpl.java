package com.task.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.dto.UserDTO;
import com.task.entity.Users;
import com.task.repository.UserRepository;
import com.task.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		try {
			
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			Users user=userDTOtoEntity(userDTO);//converted Dto to entity
			 userRepository.save(user);
			return userDTO;
			
		} catch (Exception e) {
			
			throw new UsernameNotFoundException("Exception Accured",e);
		}
	}

	
	private Users userDTOtoEntity(UserDTO userDTO)
	{
		Users user=new Users();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		return user;
		
	}
	
	
}

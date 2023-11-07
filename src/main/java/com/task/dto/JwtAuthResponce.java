package com.task.dto;

import lombok.Getter;

@Getter
public class JwtAuthResponce {
	
	
	private String token;
	
	private String tokenType="Bearer";
	
	
	public JwtAuthResponce(String token)
	{
		this.token=token;
	}

}

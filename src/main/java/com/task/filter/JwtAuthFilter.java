package com.task.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.task.security.CustomUserDetailsService;
import com.task.serviceimpl.JwtService;


@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JwtService service;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token=null;
		
		
		String username=null;
		String header=request.getHeader("Authorization");
		if(null != header && header.startsWith("Bearer"))
		{
			token=header.substring(7);
			
			username=service.extractUserName(token);
		}
		
		
		if(null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(service.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	

}

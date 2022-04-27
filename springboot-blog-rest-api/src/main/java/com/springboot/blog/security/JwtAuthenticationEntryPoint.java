package com.springboot.blog.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//To deal with JWT exceptions
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	/*
	 * This method is called whenever an exception is thrown due to an unauthorized
	 * user trying to access a resource that requires authentication
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

	}

}

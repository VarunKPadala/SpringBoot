package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

//Used to throw exception when the comment id used in the API is not related to the post id
//Also used for com.springboot.blog.security.JwtTokenProvider.validateToken(String) to throw corresponding exceptions while validating the JWT(token)
public class BlogAPIException extends RuntimeException {

	private HttpStatus status;
	private String message;

	public BlogAPIException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public BlogAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}

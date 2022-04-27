package com.springboot.blog.payload;

import java.util.Date;

import org.springframework.http.HttpStatus;

//DTO to pass the error details to user when there is an exception
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;
	private HttpStatus status;

	public ErrorDetails(Date timestamp, String message, String details, HttpStatus status) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public HttpStatus getStatus() {
		return status;
	}

}

package com.springboot.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//DTO used to send as a response to get all posts
public class PostResponse {

	// List of Posts
	private List<PostDto> content;
	// Page number used in pagination by user
	private int pageNo;
	// Page size used in pagination by user
	private int pageSize;
	// Displays total number of elements being shown in the page
	private long totalElements;
	// Total number of pages based on page size used
	private int totalPages;
	// says whether the current page we are in last page or not
	private boolean last;

}

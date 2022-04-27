package com.springboot.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	// Finds List of comments associated with a post based on post id.
	List<Comment> findByPostId(long postId);

}
package com.example.springapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);
	
}

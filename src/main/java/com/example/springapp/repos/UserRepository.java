package com.example.springapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}

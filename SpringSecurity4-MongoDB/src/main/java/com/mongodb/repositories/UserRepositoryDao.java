package com.mongodb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mongodb.domain.User;


public interface UserRepositoryDao extends CrudRepository<User, String> {
	
	
	public User findByUsername(String username);
	
	

}

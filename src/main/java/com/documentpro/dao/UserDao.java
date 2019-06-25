package com.documentpro.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	User getUserByEmailId(String emailId);
	
	User save(User user);
}

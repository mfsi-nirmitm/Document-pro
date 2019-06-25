package com.documentpro.service;

import com.documentpro.model.User;

public interface UserService {

	User getUserByEmailId(String emailId);
	
	User save(User user);
}

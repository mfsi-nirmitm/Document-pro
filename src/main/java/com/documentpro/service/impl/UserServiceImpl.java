package com.documentpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentpro.dao.UserDao;
import com.documentpro.model.User;
import com.documentpro.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByEmailId(String emailId) {
		return userDao.getUserByEmailId(emailId);
	}
	
	@Override
	public User save(User user) {
		return userDao.save(user);
	}

}

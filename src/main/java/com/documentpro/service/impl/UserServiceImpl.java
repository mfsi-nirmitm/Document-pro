package com.documentpro.service.impl;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.documentpro.constants.ConfigConstants;
import com.documentpro.dao.UserDao;
import com.documentpro.model.User;
import com.documentpro.service.FileService;
import com.documentpro.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService, ConfigConstants {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FileService fileService;

	@Override
	public User getUserByEmailId(String emailId) {
		return userDao.getUserByEmailId(emailId);
	}
	
	@Override
	public User save(User user) {
		User savedUser = userDao.save(user);
		String folderName = savedUser.getUserId().toString();
		
		File parentDir = new File(ROOT_PATH);
		
		if (!parentDir.exists()) {
			fileService.createDirectory(parentDir);
		}
		
		File childrenDir = new File(ROOT_PATH +  folderName);
		
		if (!childrenDir.exists()) {
			fileService.createDirectory(childrenDir);
		}
		
		return savedUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByEmailId(username);
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), new ArrayList<>());
	}

	@Override
	public User getUserByUserId(Long userId) {
		return userDao.getUserByUserId(userId);
	}

}

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
import com.documentpro.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService, ConfigConstants {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByEmailId(String emailId) {
		return userDao.getUserByEmailId(emailId);
	}
	
	@Override
	public User save(User user) {
		User savedUser = userDao.save(user);
		String folderName = savedUser.getUserId().toString();
		
//		File theDir = new File(ROOT_PATH + folderName);
//
//		// if the directory does not exist, create it
//		if (!theDir.exists()) {
//		    System.out.println("creating directory: " + theDir.getName());
//		    boolean result = false;
//
//		    try{
//		        theDir.mkdir();
//		        result = true;
//		    } 
//		    catch(SecurityException se){
//		        //handle it
//		    }        
//		    if(result) {    
//		        System.out.println("DIR created");  
//		    }
//		}
//		
		return savedUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByEmailId(username);
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), new ArrayList<>());
	}

}

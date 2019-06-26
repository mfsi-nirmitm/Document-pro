package com.documentpro.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.documentpro.model.User;
import com.documentpro.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestBody Map<String, String> json) throws ServletException {
		
		System.out.println("this is here");
		
		if (json.get("emailId") == null || json.get("password") == null) {
			throw new ServletException("Please fill in username and password");
		}
		
		String emailId = json.get("emailId");
		String password = json.get("password");
		
		User user = userService.getUserByEmailId(emailId);
		
		if (user == null) {
			throw new ServletException("Email'Id not found.");
		}
		
		String pwd = user.getPassword();
		
		if (!password.equals(pwd) ) {
			throw new ServletException("Invalid login. Please check your email and password");
		}
		
		return Jwts.builder().setSubject(emailId).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		return userService.save(user);
	}
	
}

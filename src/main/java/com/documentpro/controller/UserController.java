package com.documentpro.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.documentpro.dao.DocumentRepository;
import com.documentpro.dao.UserDao;
import com.documentpro.model.User;
import com.documentpro.service.JwtService;
import com.documentpro.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDao userrepo;
	
	@Autowired
	private DocumentRepository documentRepo;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestBody Map<String, String> json) throws Exception {
		
		System.out.println("this is here");
		
		if (json.get("emailId") == null || json.get("password") == null) {
			throw new ServletException("Please fill in username and password");
		}
		
		String emailId = json.get("emailId");
//		String password = encoder.encode( json.get("password") );
		String password = json.get("password");
		
		jwtService.authenticate(emailId, password);
		
		User user = userService.getUserByEmailId(emailId);
		UserDetails userDetails = userDetailsService.loadUserByUsername(emailId);
		
//		String token = jwtTokenUtil.generateToken(userDetails);
		
		if (user == null) {
			throw new ServletException("Email'Id not found.");
		}
		
		System.out.println(password);
//		password = encoder.encode(password);
		System.out.println(encoder.matches(password, user.getPassword()));
		String pwd = user.getPassword();
		System.out.println(user.getPassword());
		System.out.println(password);

		if (!encoder.matches(password, pwd)) {
			throw new ServletException("Invalid login. Please check your email and password");
		}
		
		return Jwts.builder().setSubject(emailId).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		
//		System.out.println(user.getPassword());
//		System.out.println(user.getDocuments().get(0)+"  sdsd ");
		user.setPassword(encoder.encode(user.getPassword()));
//		System.out.println(user.getPassword());
		userService.save(user);
//		User temp=userrepo.getUserByEmailId(user.getEmailId());
//		System.out.println(temp.getDocuments().get(0).getDocumentName());
//		System.out.println(temp.getDocuments().get(0).getUser());
//		System.out.println(documentRepo.getOne(temp.getDocuments().get(0).getDocumentId()).getUser()+"  ss ");
		return userService.getUserByEmailId(user.getEmailId());
	}
	
}

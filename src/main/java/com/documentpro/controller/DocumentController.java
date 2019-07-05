package com.documentpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.documentpro.model.Document;
import com.documentpro.service.DocumentService;
import com.documentpro.service.UserService;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "say-hello", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}

	@RequestMapping(value="/uploadDocument/{userid}", method = RequestMethod.POST)
	public Document saveDocument(@RequestBody Document document, @PathVariable("userid") Long userId) {
		
		document.setUser(userService.getUserByUserId(userId));
		return documentService.save(document);
	}
	
}

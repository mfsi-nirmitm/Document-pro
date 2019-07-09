package com.documentpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentpro.service.DocumentService;
import com.documentpro.service.FileService;
import com.documentpro.service.UserService;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "say-hello", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}

	@RequestMapping(value="/uploadDocument/{userid}", method = RequestMethod.POST)
	public void saveDocument(@RequestBody MultipartFile file, @PathVariable("userid") Long userId) {

		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());

		fileService.transferFile(file, userId);

		
//		document.setUser(userService.getUserByUserId(userId));
//		return documentService.save(document);
	}
	
}

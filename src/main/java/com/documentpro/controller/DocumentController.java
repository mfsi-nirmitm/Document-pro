package com.documentpro.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public void saveDocument(@RequestBody MultipartFile file, @PathVariable("userid") Long userId) {
		
		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());
		
		try {
			Path destPath = Paths.get("documents");
			System.out.println(destPath.toAbsolutePath());
			File destFile = new File(destPath.toAbsolutePath()+"\\"+userId+"\\"+file.getOriginalFilename());
			file.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		document.setUser(userService.getUserByUserId(userId));
//		return documentService.save(document);
	}
	
}

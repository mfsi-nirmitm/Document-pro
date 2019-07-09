package com.documentpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentpro.model.Document;
import com.documentpro.service.DocumentService;
import com.documentpro.service.FileService;
import com.documentpro.service.UserService;
import com.documentpro.service.VersionService;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private VersionService versionService;
	
	@RequestMapping(value = "say-hello", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}

	@RequestMapping(value="/uploadDocument/{userid}", method = RequestMethod.POST)
	public boolean saveDocument(@RequestBody MultipartFile file, @PathVariable("userid") Long userId) {

		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());

		boolean isSaved = fileService.transferFile(file, userId);

		if ( isSaved ) {
			
			String fileName[] = file.getOriginalFilename().split("\\.");
			System.out.println(fileName);
			Document document = new Document();
			document.setLatestVersion(0l);
			document.setDocumentType(fileName[1]);
			document.setDocumentName(fileName[0]+document.getLatestVersion());
			document.setUser(userService.getUserByUserId(userId));
			
			Document savedDocument = documentService.save(document);
			
			if ( savedDocument != null) {
				versionService.createNewVersion(0l, savedDocument.getDocumentId());
				return true;
			}
		}
	
		return false;
	}
	
}

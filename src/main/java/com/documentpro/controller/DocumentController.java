package com.documentpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentpro.model.Document;
import com.documentpro.model.User;
import com.documentpro.model.Version;
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

		String fileName[] = file.getOriginalFilename().split("\\.");
		Document document = new Document();
		document.setLatestVersion(0l);
		document.setDocumentType(fileName[1]);
		document.setDocumentName(fileName[0]);
		document.setUser(userService.getUserByUserId(userId));

		Document savedDocument = documentService.save(document);

		if ( savedDocument != null) {

			Version versionEntity = versionService.createNewVersion(0l, savedDocument.getDocumentId());
			boolean isSaved = fileService.transferFile(file, userId, versionEntity.getVersionName());

			if (isSaved) {

				return true;

			}

		}

		return false;
		
	}

	@RequestMapping(value="/documentList/{userId}", method = RequestMethod.GET)
	public List<Document> getDocumentList(@PathVariable("userId") Long userId) {
		
		User user = userService.getUserByUserId(userId);
		
		return user.getDocuments();
		
	}
	
	@RequestMapping(value="/deleteDocument/{userId}/{documentId}", method = RequestMethod.DELETE )
	public boolean deleteDocument(@PathVariable("userId") Long userId, @PathVariable("documentId") Long documentId) {

		User user = userService.getUserByUserId(userId);

		Document document = documentService.findByUserAndDocumentId(user, documentId);

		System.out.println(document.getDocumentName());

		return false;

	}

}

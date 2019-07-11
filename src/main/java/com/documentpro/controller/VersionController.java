package com.documentpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentpro.model.Document;
import com.documentpro.model.Version;
import com.documentpro.service.DocumentService;
import com.documentpro.service.FileService;
import com.documentpro.service.HistoryService;
import com.documentpro.service.VersionService;

@RestController
@RequestMapping("/rest")
public class VersionController {
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private VersionService versionService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private HistoryService historyService;

	@RequestMapping(value="/uploadNewVersion/{userId}/{documentId}", method = RequestMethod.POST)
	public boolean uploadNewVersion(@RequestBody MultipartFile file, 
									@PathVariable("userId") Long userId, 
									@PathVariable("documentId") Long documentId) {
		
		Document documentEntity = documentService.getDocumentByDocumentId(documentId);
		Long latestVersion = documentEntity.getLatestVersion();
		
		Version versionEntity = versionService.createNewVersion(latestVersion + 1 , documentId);
		boolean isSaved = fileService.transferFile(file, userId, versionEntity.getVersionName());
		
		if (isSaved) {
			
			documentEntity.setLatestVersion(latestVersion+1);
			documentService.save(documentEntity);
			historyService.makeHistory("Uploaded the new version of document "+ documentEntity.getDocumentName());
			
			return true;
			
		}
		
		return false;
		
	}
	
}

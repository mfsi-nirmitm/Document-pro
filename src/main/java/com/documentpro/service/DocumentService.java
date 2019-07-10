package com.documentpro.service;

import com.documentpro.model.Document;
import com.documentpro.model.User;

public interface DocumentService {

	public Document save(Document document);
	
	public Document getDocumentByDocumentId(Long documentId);
	
	Document findByUserAndDocumentId(User user, Long documentId);
	
}

package com.documentpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentpro.dao.DocumentRepository;
import com.documentpro.model.Document;
import com.documentpro.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepo;
	
	@Override
	public Document save(Document document) {
		return documentRepo.save(document);
	}

}

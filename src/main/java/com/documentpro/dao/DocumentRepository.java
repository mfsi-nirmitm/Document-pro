package com.documentpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.Document;
import com.documentpro.model.User;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document getDocumentByDocumentId(Long documentId);
	
	Document findByUserAndDocumentId(User user, Long documentId);
	
}

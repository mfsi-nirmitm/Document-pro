package com.documentpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document getDocumentByDocumentId(Long documentId);
	
}

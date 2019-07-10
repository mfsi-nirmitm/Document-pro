package com.documentpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentpro.dao.DocumentRepository;
import com.documentpro.dao.VersionRepository;
import com.documentpro.model.Version;
import com.documentpro.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	private DocumentRepository documentRepo;
	
	@Autowired
	private VersionRepository versionRepo;
	
	@Override
	public Version createNewVersion(long version, long documentId) {
		
		Version versionEntity = new Version();
		versionEntity.setDocument(documentRepo.getDocumentByDocumentId(documentId));
		versionEntity.setVersionName(getVersionName(version));
		
		return saveVersion(versionEntity);
		
	}

	@Override
	public String getVersionName(long version) {
		return version+"v";
	}

	@Override
	public Version saveVersion(Version versionEntity) {
		return versionRepo.save(versionEntity);
	}

	@Override
	public Long getLatestVersion(Long documentId) {
		return documentRepo.getDocumentByDocumentId(documentId).getLatestVersion();
	}

}

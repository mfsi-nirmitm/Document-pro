package com.documentpro.service;

import com.documentpro.model.Version;

public interface VersionService {

	public Version createNewVersion(long version, long documentId);
	
	public String getVersionName(long version);
	
	public Version saveVersion(Version versionEntity);
	
	public Long getLatestVersion(Long documentId);
}

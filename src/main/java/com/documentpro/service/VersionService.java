package com.documentpro.service;

import com.documentpro.model.Version;

public interface VersionService {

	public boolean createNewVersion(long version, long documentId);
	
	public String getVersionName(long version);
	
	public boolean saveVersion(Version versionEntity);
}

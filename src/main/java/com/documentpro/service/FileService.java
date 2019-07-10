package com.documentpro.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public void createDirectory(File theDir);
	
	public boolean transferFile(MultipartFile file, Long userId, String VersionName);
}

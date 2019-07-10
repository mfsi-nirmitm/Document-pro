package com.documentpro.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.documentpro.constants.ConfigConstants;
import com.documentpro.service.FileService;

@Service
public class FileServiceImpl implements FileService, ConfigConstants {

	@Override
	public void createDirectory(File theDir) {
		
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		    	
		        theDir.mkdir();
		        result = true;
		    
		    } 
		    catch(SecurityException se){
		        
		    }        
		    if(result) {    
		    	
		        System.out.println("DIR created");  
		        
		    }
		    
		}
		
	}

	@Override
	public boolean transferFile(MultipartFile file, Long userId, String versionName) {
		
		boolean result = false;
		
		try {
			
			Path destPath = Paths.get(ROOT_PATH);
			System.out.println(destPath.toAbsolutePath());
			String fileName[] = file.getOriginalFilename().split("\\.");
			File destFile = new File(destPath.toAbsolutePath()+"\\"+userId+"\\"+fileName[0]+versionName+"."+fileName[1]);
			file.transferTo(destFile);
			result = true;
			
		} catch (IllegalStateException | IOException e) {
			
			result = false;
			e.printStackTrace();
			
		}
		
		return result;
	
	}

}

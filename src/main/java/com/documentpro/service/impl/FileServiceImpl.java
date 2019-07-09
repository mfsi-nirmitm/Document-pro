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
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
	}

	@Override
	public void transferFile(MultipartFile file, Long userId) {
		
		try {
			Path destPath = Paths.get(ROOT_PATH);
			System.out.println(destPath.toAbsolutePath());
			File destFile = new File(destPath.toAbsolutePath()+"\\"+userId+"\\"+file.getOriginalFilename());
			file.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

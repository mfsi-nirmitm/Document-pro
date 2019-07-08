package com.documentpro.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.documentpro.service.FileService;

@Service
public class FileServiceImpl implements FileService {

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

}

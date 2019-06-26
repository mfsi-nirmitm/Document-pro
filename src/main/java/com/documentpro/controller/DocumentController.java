package com.documentpro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class DocumentController {

	@RequestMapping(value = "say-hello", method = RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}
	
}

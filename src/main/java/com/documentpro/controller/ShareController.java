package com.documentpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.documentpro.dto.ShareDTO;
import com.documentpro.dto.SharedDocumentDTO;
import com.documentpro.model.Document;
import com.documentpro.model.Share;
import com.documentpro.model.User;
import com.documentpro.service.DocumentService;
import com.documentpro.service.ShareService;
import com.documentpro.service.UserService;

@RestController
@RequestMapping("/rest")
public class ShareController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private ShareService shareService;

	@RequestMapping(value = "/shareDocument", method = RequestMethod.POST )
	public boolean shareDocumentBetweenUser(@RequestBody ShareDTO share ) {
		
		User user = userService.getUserByUserId(share.getUserId());
		Document document = documentService.getDocumentByDocumentId(share.getDocumentId());
		
		Share shareEntity = shareService.shareToUser(user, document, share.getPermission());
		
		if (shareEntity != null) {
		
			return true;
		
		}
		
		return false;
		
	}

	@RequestMapping(value = "/sharedDocumentList/{userId}", method = RequestMethod.GET )
	public List<Share> sharedDocumentList(@PathVariable("userId") Long userId) {
		
		return shareService.documentsSharedWithUser(userService.getUserByUserId(userId));
		
	}
	
}

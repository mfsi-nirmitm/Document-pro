package com.documentpro.service;

import java.util.List;

import com.documentpro.model.Document;
import com.documentpro.model.Share;
import com.documentpro.model.User;

public interface ShareService {

	public Share shareToUser(User user, Document document, String permission);
	
	public List<Share> documentsSharedWithUser(User user);
	
}

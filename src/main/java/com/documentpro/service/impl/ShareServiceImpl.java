package com.documentpro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentpro.dao.ShareRepository;
import com.documentpro.dao.UserDao;
import com.documentpro.dto.SharedDocumentDTO;
import com.documentpro.model.Document;
import com.documentpro.model.Share;
import com.documentpro.model.User;
import com.documentpro.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService {
	
	@Autowired
	private ShareRepository shareRepo;
	
	@Autowired
	private UserDao userRepo;

	@Override
	public Share shareToUser(User user, Document document, String permission) {
		
		Share share = new Share();
		share.setUser(user);
		share.setDocument(document);
		share.setPermission(permission);
		
		return shareRepo.save(share);
		
	}

	@Override
	public List<Share> documentsSharedWithUser(User user) {
		
		return shareRepo.findByUser(user);
		
	}
	
//	public List<SharedDocumentDTO> getSharedDocumentListForUser(Long userId) {
//		
//		List<Share> shares = shareRepo.findByUser(userRepo.getUserByUserId(userId));
//		
//		List<SharedDocumentDTO> sharedDocumentDtoList = new ArrayList<SharedDocumentDTO>();
//		
//		for (Share share : shares) {
//			
//			SharedDocumentDTO sharedDocumentDto = new SharedDocumentDTO();
//			sharedDocumentDto.setPermission(share.getPermission());
//			
//			
//		}
//		
//	}

}

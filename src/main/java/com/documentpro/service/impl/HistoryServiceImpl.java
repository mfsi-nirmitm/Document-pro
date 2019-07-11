package com.documentpro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentpro.dao.HistoryRepository;
import com.documentpro.model.History;
import com.documentpro.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryRepository historyRepo;
	
	@Override
	public History makeHistory(String description) {
		
		History history = new History();
		history.setDescription(description);
		return historyRepo.save(history);
				
	}

	
}

package com.documentpro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.Share;
import com.documentpro.model.User;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

	public List<Share> findByUser(User user);
	
}

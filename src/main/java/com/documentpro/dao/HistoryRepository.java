package com.documentpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}

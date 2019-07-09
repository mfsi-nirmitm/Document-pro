package com.documentpro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentpro.model.Version;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {

}

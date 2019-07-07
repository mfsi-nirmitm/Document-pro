package com.documentpro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Version {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="version_id")
	private Long versionId;	

	@Column(name="created_date")
	private Date creationDate;
	
	@Column(name="version_name")
	private String versionName;
	
	@JsonBackReference
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="document_id")
	private Document document;

	@PrePersist
	protected void onCreate() {
		creationDate = new Date();
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}

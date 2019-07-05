package com.documentpro.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Document {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="document_id")
	private Long documentId;

	@Column(name="document_name")
	private String documentName;

	@Column(name="latest_version")
	private Long latestVersion;
	
	@JsonManagedReference
	@OneToMany(mappedBy="document", cascade=CascadeType.ALL)
	public List<Version> versions;

	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public Long getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(Long latestVersion) {
		this.latestVersion = latestVersion;
	}
	
	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

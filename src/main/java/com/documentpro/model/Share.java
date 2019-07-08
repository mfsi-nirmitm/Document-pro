package com.documentpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Share {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="shared_id")
	private Long sharedId;
	
	@Column(name="permission")
	private String permission;
	
//	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="documentId")
	private Document document;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
//	@JsonManagedReference
//	@OneToMany(mappedBy="user" , cascade= { CascadeType.ALL })
//	public List<User> users;
	
//	@ManyToMany(mappedBy="user")
//	private List<User> users;

	public Long getSharedId() {
		return sharedId;
	}

	public void setSharedId(Long sharedId) {
		this.sharedId = sharedId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

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
public class History {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "history_id" )
	private Long historyId;
	
	@Column( name = "description" )
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "userId" )
	private User user;

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

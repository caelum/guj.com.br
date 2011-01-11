package br.com.caelum.guj.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NewsletterParticipant {

	@Id
	private Integer gujUserId;

	private String email;

	public NewsletterParticipant() {
	}

	public NewsletterParticipant(Integer gujUserId, String email) {
		this.gujUserId = gujUserId;
		this.email = email;
	}

	public NewsletterParticipant(Integer gujUserId) {
		this.gujUserId = gujUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGujUserId() {
		return this.gujUserId;
	}

	public void setGujUserId(Integer gujUserId) {
		this.gujUserId = gujUserId;
	}
}

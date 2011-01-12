package br.com.caelum.guj.controller;

import br.com.caelum.guj.dao.GUJUserDAO;
import br.com.caelum.guj.model.NewsletterParticipant;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class NewsletterController {

	private final GUJUserDAO dao;

	public NewsletterController(GUJUserDAO dao) {
		this.dao = dao;
	}

	@Post
	@Path(value = "/newsletter/")
	public void register(Integer gujUserId) {

		NewsletterParticipant participant = this.dao.findParticipantByGujUserId(gujUserId);

		if (participant == null) {
			String email = this.dao.emailFromUser(gujUserId);
			NewsletterParticipant p = new NewsletterParticipant(gujUserId, email);
			// aqui registra no mailchimp
			this.dao.registerNewsletterParticipant(p);
		}
	}

	@Delete
	@Path(value = "/newsletter/")
	public void unregister(Integer gujUserId) {
		NewsletterParticipant participant = this.dao.findParticipantByGujUserId(gujUserId);

		if (participant != null) {
			// aqui desregistra no mailchimp
			this.dao.unregisterNewsletterParticipant(participant);
		}
	}
}

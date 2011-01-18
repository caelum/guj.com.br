package br.com.caelum.guj.controller;

import javax.servlet.http.HttpSession;

import br.com.caelum.guj.dao.GUJUserDAO;
import br.com.caelum.guj.model.NewsletterParticipant;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class NewsletterController {

	private final GUJUserDAO dao;
	private final HttpSession session;
	private final Result result;

	public NewsletterController(GUJUserDAO dao, HttpSession session, Result result) {
		this.dao = dao;
		this.session = session;
		this.result = result;
	}

	@Post
	@Path(value = "/newsletter/")
	public void register(Integer gujUserId) {

		NewsletterParticipant participant = this.dao.findParticipantByGujUserId(gujUserId);

		if (participant == null) {
			String email = this.dao.emailFromUser(gujUserId);
			NewsletterParticipant p = new NewsletterParticipant(gujUserId, email);

			// aqui registra no mailchimp e seta na sessao q o cara agora eh
			// true
			this.dao.registerNewsletterParticipant(p);
			session.setAttribute("newsletterParticipant", true);

		}
		result.use(Results.logic()).redirectTo(HomeController.class).index();
	}

	@Delete
	@Path(value = "/newsletter/")
	public void unregister(Integer gujUserId) {
		NewsletterParticipant participant = this.dao.findParticipantByGujUserId(gujUserId);

		if (participant != null) {
			// aqui desregistra no mailchimp
			this.dao.unregisterNewsletterParticipant(participant);
			session.setAttribute("newsletterParticipant", false);
		}
		result.use(Results.logic()).redirectTo(HomeController.class).index();
	}
}

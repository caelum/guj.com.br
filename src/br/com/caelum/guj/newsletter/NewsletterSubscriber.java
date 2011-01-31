package br.com.caelum.guj.newsletter;

import org.apache.log4j.Logger;

import br.com.caelum.guj.model.NewsletterParticipant;
import br.com.caelum.guj.newsletter.request.NewsletterRequest;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class NewsletterSubscriber {

	private static final Logger LOGGER = Logger.getLogger(NewsletterSubscriber.class);
	private final NewsletterRequest request;
	private final NewsletterURIBuilder uriBuilder;

	public NewsletterSubscriber(NewsletterURIBuilder uriBuilder, NewsletterRequest request) {
		this.uriBuilder = uriBuilder;
		this.request = request;
	}

	public void subscribe(NewsletterParticipant participant) {
		subscribe(participant, false);
	}

	public void subscribeWithConfirmation(NewsletterParticipant participant) {
		subscribe(participant, true);
	}

	private void subscribe(NewsletterParticipant participant, boolean sendConfirmation) {
		String uri = uriBuilder.emailAddress(participant.getEmail()).optin(sendConfirmation).genereateURI();
		request.executeRequest(uri);
		LOGGER.info("Registering the email " + participant.getEmail() + " at the newsletter");
	}
}

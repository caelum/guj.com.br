package br.com.caelum.guj.newsletter;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class NewsletterURIBuilder {
	private String subscribeURI = "http://<dc>.api.mailchimp.com/1.3/?"
			+ "output=xml&method=listSubscribe&apikey=<apikey>&email_address=<emailaddress>&double_optin=<optin>&id=<listid>";

	public NewsletterURIBuilder(NewsletterConfigs configs) {
		subscribeURI = subscribeURI.replace("<dc>", configs.getDc()).replace("<apiKey>", configs.getAPIKey())
				.replace("<listid>", configs.getListId());
	}

	public String genereateURI() {
		return subscribeURI;
	}

	public NewsletterURIBuilder emailAddress(String email) {
		subscribeURI = subscribeURI.replace("<emailaddress>", email);
		return this;
	}

	public NewsletterURIBuilder optin(boolean optin) {
		subscribeURI = subscribeURI.replace("<optin>", Boolean.valueOf(optin).toString());
		return this;
	}
}
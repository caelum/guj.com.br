package br.com.caelum.guj.newsletter;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import br.com.caelum.guj.model.NewsletterParticipant;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class NewsletterManager {
	private static final String SUBSCRIBE_URI_TEMPLATE = "http://<dc>.api.mailchimp.com/1.3/?"
			+ "output=xml&method=listSubscribe&apikey=<apikey>&email_address=<emailaddress>&double_optin=false&id=<listid>";

	private final NewsletterConfigs configs;

	private static final Logger LOGGER = Logger.getLogger(NewsletterManager.class);

	public NewsletterManager(NewsletterConfigs configs) {
		this.configs = configs;
	}

	public void subscribe(NewsletterParticipant participant) {
		String subscribeURI = SUBSCRIBE_URI_TEMPLATE.replace("<dc>", configs.getDc())
				.replace("<emailaddress>", participant.getEmail()).replace("<apikey>", configs.getAPIKey())
				.replace("<listid>", configs.getListId());

		LOGGER.info("Registering the email " + participant.getEmail() + " at the newsletter");

		HttpMethod get = new GetMethod(subscribeURI);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(get);
			String response = get.getResponseBodyAsString();
			LOGGER.info("The response for " + participant.getEmail() + " is: " + response);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			get.releaseConnection();
		}

	}

	public void unsubscribe() {

	}
}

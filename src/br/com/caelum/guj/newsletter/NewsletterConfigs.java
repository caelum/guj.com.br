package br.com.caelum.guj.newsletter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class NewsletterConfigs {

	private String key;
	private String listId;

	@PostConstruct
	public void initialize() throws IOException {
		Properties properties = new Properties();
		InputStream stream = NewsletterConfigs.class.getResourceAsStream("/newsletter_config.properties");
		properties.load(stream);
		key = (String) properties.get("newsletter_key");
		listId = (String) properties.get("list_id");
		stream.close();
	}

	public String getAPIKey() {
		return key;
	}

	public String getListId() {
		return listId;
	}
}

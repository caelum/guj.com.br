package br.com.caelum.guj.newsletter.request;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class NewsletterRequest {
	public void executeRequest(String url) {
		HttpMethod get = new GetMethod(url);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(get);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			get.releaseConnection();
		}
	}
}

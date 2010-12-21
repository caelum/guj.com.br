package br.com.caelum.guj.uri;

public class URITermsExtractor {

	private final String uri;

	public URITermsExtractor(String uri) {
		this.uri = uri;
	}

	public String getId() {

		// "http://localhost:8080/guj.com.br/post/228076/uma-uri-de-teste"
		String[] splittedUri = this.uri.split("/");
		return splittedUri[splittedUri.length - 2];
	}

}

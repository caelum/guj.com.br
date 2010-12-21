package br.com.caelum.guj.uri;

public class URITermsExtractor {

	private final String[] splittedUri;

	public URITermsExtractor(String uri) {
		this.splittedUri = uri.split("/");
	}

	private String getId() {

		// "http://localhost:8080/guj.com.br/post/228076/uma-uri-de-teste"

		return this.tokenInPosition(2);
	}

	public boolean isBookmarkable() {
		return this.tokenInPosition(3).equals("post");
	}

	private String tokenInPosition(int position) {
		return this.splittedUri[this.splittedUri.length - position];
	}

	public String buildCompatibleURI() {
		return String.format("/posts/list/%s.java", this.getId());
	}
}

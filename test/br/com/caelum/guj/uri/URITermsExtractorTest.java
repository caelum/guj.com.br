package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class URITermsExtractorTest {

	@Test
	public void givenAPostURIItShouldExtractId() throws Exception {
		URITermsExtractor uriTermsExtractor = new URITermsExtractor(
				"http://localhost:8080/guj.com.br/post/228076/uma-uri-de-teste");
		String id = uriTermsExtractor.getId();

		assertEquals("228076", id);
	}
}

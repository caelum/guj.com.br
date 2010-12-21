package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class URITermsExtractorTest {

	private URITermsExtractor uriTermsExtractor;

	@Before
	public void setUp() {
		this.uriTermsExtractor = new URITermsExtractor("/post/228076/uma-uri-de-teste");
	}

	@Test
	public void shouldBeAPostBookmarkableURI() {
		assertTrue(this.uriTermsExtractor.isBookmarkable());

	}

	@Test
	public void shouldReturnCompatibleURI() {
		String compatibleURI = this.uriTermsExtractor.buildCompatibleURI();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

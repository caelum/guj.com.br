package br.com.caelum.guj.uri;

import static br.com.caelum.guj.uri.RequestInfoBuilder.aRequestFor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.guj.uri.bookmarkable.BookmarkablePrePostToCompatibleURIConverter;

public class BookmarkablePrePostToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {
		BookmarkablePrePostToCompatibleURIConverter validBuilder = new BookmarkablePrePostToCompatibleURIConverter(
				aRequestFor("/guj.com.br/prepost/228076/1/uma-uri-de-teste"));
		assertTrue(validBuilder.isConvertable());

		BookmarkablePrePostToCompatibleURIConverter invalidBuilder = new BookmarkablePrePostToCompatibleURIConverter(
				aRequestFor(""));
		assertFalse(invalidBuilder.isConvertable());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkablePrePostToCompatibleURIConverter builder = new BookmarkablePrePostToCompatibleURIConverter(
				aRequestFor("/prepost/228076/1/uma-uri-de-teste"));
		String compatibleURI = builder.convert();

		assertEquals("/posts/preList/228076/1.java", compatibleURI);
	}

	@Test
	public void shouldReturnCompatibleURI1() {
		BookmarkablePrePostToCompatibleURIConverter builder = new BookmarkablePrePostToCompatibleURIConverter(
				aRequestFor("/prepost/228076/1/uma-uri-de-teste"));
		String compatibleURI = builder.convert();

		assertEquals("/posts/preList/228076/1.java", compatibleURI);
	}
}

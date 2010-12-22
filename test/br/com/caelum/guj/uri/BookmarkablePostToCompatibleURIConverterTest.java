package br.com.caelum.guj.uri;

import static br.com.caelum.guj.uri.RequestInfoBuilder.aRequestFor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.guj.uri.bookmarkable.BookmarkablePostToCompatibleURIConverter;

public class BookmarkablePostToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {

		BookmarkablePostToCompatibleURIConverter validConverter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/guj.com.br/java/228076-uma-uri-de-teste"));
		assertTrue(validConverter.isConvertable());

		BookmarkablePostToCompatibleURIConverter invalidConverter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor(""));
		assertFalse(invalidConverter.isConvertable());
	}

	@Test
	public void shouldReturnCompatiblePaginatedURI() {
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/java/228076-uma-uri-de-teste/2"));
		assertEquals("/posts/list/15/228076.java", converter.convert());

		converter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/java/228076-uma-uri-de-teste/10"));
		assertEquals("/posts/list/135/228076.java", converter.convert());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/java/228076-uma-uri-de-teste"));
		String compatibleURI = converter.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}

	@Test
	public void shouldReturnCompatibleURI1() {
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/java/228076-uma-uri-de-teste"));
		String compatibleURI = converter.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

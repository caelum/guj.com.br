package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkableToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {
		BookmarkableToCompatibleURIConverter validBuilder = new BookmarkableToCompatibleURIConverter(
				"/guj.com.br/post/228076/uma-uri-de-teste");
		assertTrue(validBuilder.isBookmarkable());

		BookmarkableToCompatibleURIConverter invalidBuilder = new BookmarkableToCompatibleURIConverter("");
		assertFalse(invalidBuilder.isBookmarkable());
	}

	@Test
	public void shouldReturnCompatiblePaginatedURI() {
		BookmarkableToCompatibleURIConverter builder = new BookmarkableToCompatibleURIConverter(
				"/post/228076/uma-uri-de-teste", "2");
		assertEquals("/posts/list/15/228076.java", builder.convert());

		builder = new BookmarkableToCompatibleURIConverter("/post/228076/uma-uri-de-teste", "10");
		assertEquals("/posts/list/135/228076.java", builder.convert());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkableToCompatibleURIConverter builder = new BookmarkableToCompatibleURIConverter("/post/228076/uma-uri-de-teste");
		String compatibleURI = builder.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}

	@Test
	public void shouldReturnCompatibleURI1() {
		BookmarkableToCompatibleURIConverter builder = new BookmarkableToCompatibleURIConverter("/post/228076/uma-uri-de-teste");
		String compatibleURI = builder.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

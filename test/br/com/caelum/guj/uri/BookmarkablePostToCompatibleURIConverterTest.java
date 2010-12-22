package br.com.caelum.guj.uri;

import static br.com.caelum.guj.uri.RequestInfoBuilder.aRequestFor;
import static br.com.caelum.guj.uri.RequestInfoBuilder.inPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkablePostToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {

		BookmarkablePostToCompatibleURIConverter validBuilder = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/guj.com.br/post/228076/uma-uri-de-teste"));
		assertTrue(validBuilder.isBookmarkable());

		BookmarkablePostToCompatibleURIConverter invalidBuilder = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor(""));
		assertFalse(invalidBuilder.isBookmarkable());
	}

	@Test
	public void shouldReturnCompatiblePaginatedURI() {
		BookmarkablePostToCompatibleURIConverter builder = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/post/228076/uma-uri-de-teste", inPage(2)));
		assertEquals("/posts/list/15/228076.java", builder.convert());

		builder = new BookmarkablePostToCompatibleURIConverter(aRequestFor(
				"/post/228076/uma-uri-de-teste", inPage(10)));
		assertEquals("/posts/list/135/228076.java", builder.convert());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkablePostToCompatibleURIConverter builder = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/post/228076/uma-uri-de-teste"));
		String compatibleURI = builder.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}

	@Test
	public void shouldReturnCompatibleURI1() {
		BookmarkablePostToCompatibleURIConverter builder = new BookmarkablePostToCompatibleURIConverter(
				aRequestFor("/post/228076/uma-uri-de-teste"));
		String compatibleURI = builder.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

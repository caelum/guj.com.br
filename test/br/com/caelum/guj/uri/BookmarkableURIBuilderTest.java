package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkableURIBuilderTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {
		BookmarkableURIBuilder validBuilder = new BookmarkableURIBuilder(
				"/guj.com.br/post/228076/uma-uri-de-teste");
		assertTrue(validBuilder.isBookmarkable());

		BookmarkableURIBuilder invalidBuilder = new BookmarkableURIBuilder("");
		assertFalse(invalidBuilder.isBookmarkable());
	}

	@Test
	public void shouldReturnCompatiblePaginatedURI() {
		BookmarkableURIBuilder builder = new BookmarkableURIBuilder(
				"/post/228076/uma-uri-de-teste", "2");
		assertEquals("/posts/list/15/228076.java", builder.buildCompatibleURI());

		builder = new BookmarkableURIBuilder("/post/228076/uma-uri-de-teste", "10");
		assertEquals("/posts/list/135/228076.java", builder.buildCompatibleURI());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkableURIBuilder builder = new BookmarkableURIBuilder("/post/228076/uma-uri-de-teste");
		String compatibleURI = builder.buildCompatibleURI();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}

	@Test
	public void shouldReturnCompatibleURI1() {
		BookmarkableURIBuilder builder = new BookmarkableURIBuilder("/post/228076/uma-uri-de-teste");
		String compatibleURI = builder.buildCompatibleURI();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

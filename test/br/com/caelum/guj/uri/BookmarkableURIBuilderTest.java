package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkableURIBuilderTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {
		BookmarkableURIBuilder validBuilder = new BookmarkableURIBuilder(
				"/post/228076/uma-uri-de-teste");
		assertTrue(validBuilder.isBookmarkable());

		BookmarkableURIBuilder invalidBuilder = new BookmarkableURIBuilder("");
		assertFalse(invalidBuilder.isBookmarkable());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		BookmarkableURIBuilder builder = new BookmarkableURIBuilder("/post/228076/uma-uri-de-teste");
		String compatibleURI = builder.buildCompatibleURI();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

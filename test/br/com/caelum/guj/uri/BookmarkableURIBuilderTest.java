package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BookmarkableURIBuilderTest {

	private BookmarkableURIBuilder builder;

	@Before
	public void setUp() {
		this.builder = new BookmarkableURIBuilder("/post/228076/uma-uri-de-teste");
	}

	@Test
	public void shouldBeAPostBookmarkableURI() {
		assertTrue(this.builder.isBookmarkable());

	}

	@Test
	public void shouldReturnCompatibleURI() {
		String compatibleURI = this.builder.buildCompatibleURI();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
}

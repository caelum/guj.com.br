package br.com.caelum.guj.uri.bookmarkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.guj.uri.CompatibleURIBuilder;
import br.com.caelum.guj.uri.bookmarkable.BookmarkablePostToCompatibleURIConverter;
import static org.mockito.Mockito.*;

public class BookmarkablePostToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {

		BookmarkablePostToCompatibleURIConverter validConverter = new BookmarkablePostToCompatibleURIConverter(
				"/guj.com.br/java/228076-uma-uri-de-teste", aBuilder());
		assertTrue(validConverter.isConvertable());

		BookmarkablePostToCompatibleURIConverter invalidConverter = new BookmarkablePostToCompatibleURIConverter(
				"", aBuilder());
		assertFalse(invalidConverter.isConvertable());
	}

	@Test
	public void shouldReturnCompatiblePaginatedURI() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatibleURL("228076", 15)).thenReturn("/posts/list/15/228076.java");
		when(builder.compatibleURL("228076", 135)).thenReturn("/posts/list/135/228076.java");
		
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				"/java/228076-uma-uri-de-teste/2", builder);
		assertEquals("/posts/list/15/228076.java", converter.convert());

		converter = new BookmarkablePostToCompatibleURIConverter(
				"/java/228076-uma-uri-de-teste/10", builder);
		assertEquals("/posts/list/135/228076.java", converter.convert());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatibleURL("228076")).thenReturn("/posts/list/228076.java");
		
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				"/java/228076-uma-uri-de-teste", builder);
		String compatibleURI = converter.convert();

		assertEquals("/posts/list/228076.java", compatibleURI);
	}
	
	// XXX rename
	@Test
	public void shouldReturnCompatibleURI2() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatibleURL("228076")).thenReturn("/posts/list/228076.java");
		
		BookmarkablePostToCompatibleURIConverter converter = new BookmarkablePostToCompatibleURIConverter(
				"/java/228076", builder);
		String compatibleURI = converter.convert();
		
		assertTrue(converter.isConvertable());
		assertEquals("/posts/list/228076.java", compatibleURI);
	}

	private CompatibleURIBuilder aBuilder() {
		return mock(CompatibleURIBuilder.class);
	}
}

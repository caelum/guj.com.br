package br.com.caelum.guj.uri.bookmarkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;

import br.com.caelum.guj.uri.CompatibleURIBuilder;
import br.com.caelum.guj.uri.bookmarkable.BookmarkablePrePostToCompatibleURIConverter;

public class BookmarkablePrePostToCompatibleURIConverterTest {

	@Test
	public void shouldBeAPostBookmarkableURI() {
		BookmarkablePrePostToCompatibleURIConverter validBuilder = new BookmarkablePrePostToCompatibleURIConverter(
				"/guj.com.br/prepost/228076/1/uma-uri-de-teste", aBuilder());
		assertTrue(validBuilder.isConvertable());

		BookmarkablePrePostToCompatibleURIConverter invalidBuilder = new BookmarkablePrePostToCompatibleURIConverter(
				"", aBuilder());
		assertFalse(invalidBuilder.isConvertable());
	}

	@Test
	public void shouldReturnCompatibleURI() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatiblePrePostURL("228076", "1")).thenReturn("/posts/preList/228076/1.java");
		
		BookmarkablePrePostToCompatibleURIConverter converter = new BookmarkablePrePostToCompatibleURIConverter(
				"/prepost/228076/1/uma-uri-de-teste", builder);
		String compatibleURI = converter.convert();

		assertEquals("/posts/preList/228076/1.java", compatibleURI);
	}
	
	private CompatibleURIBuilder aBuilder() {
		return mock(CompatibleURIBuilder.class);
	}
}

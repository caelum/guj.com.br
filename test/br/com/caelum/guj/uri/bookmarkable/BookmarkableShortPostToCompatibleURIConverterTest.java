package br.com.caelum.guj.uri.bookmarkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.com.caelum.guj.uri.CompatibleURIBuilder;

public class BookmarkableShortPostToCompatibleURIConverterTest {
	private BookmarkableShortPostToCompatibleURIConverter converter;
	
	@Test
	public void shouldReturnCompatibleURI() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatibleURL("228076")).thenReturn("/posts/list/228076.java");
		
		converter = new BookmarkableShortPostToCompatibleURIConverter(
				"/java/228076", builder);
		String compatibleURI = converter.convert();
		
		assertTrue(converter.isConvertable());
		assertEquals("/posts/list/228076.java", compatibleURI);
	}
	
	@Test
	public void shouldNotMatchRegularBookmarkableURI() {
		CompatibleURIBuilder builder = aBuilder();
		when(builder.compatibleURL("228076")).thenReturn("/posts/list/228076.java");
		
		converter = new BookmarkableShortPostToCompatibleURIConverter(
				"/java/228076-algum-post", builder);
		
		assertFalse(converter.isConvertable());
	}

	private CompatibleURIBuilder aBuilder() {
		return mock(CompatibleURIBuilder.class);
	}
}

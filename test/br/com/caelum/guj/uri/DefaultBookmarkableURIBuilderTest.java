package br.com.caelum.guj.uri;

import org.junit.Test;

import br.com.caelum.guj.view.Slugger;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DefaultBookmarkableURIBuilderTest {
	
	@Test
	public void shouldGeneratePaginatedURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");
		
		DefaultBookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande", 3);
		
		assertEquals("/java/123-titulo-grande/3", uri);
	}

	@Test
	public void shouldGeneratePaginatedURIWithAnchor() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");
		
		DefaultBookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande", 3, "456");
		
		assertEquals("/java/123-titulo-grande/3#456", uri);
	}

	@Test
	public void shouldGenerateSimpleURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");
		
		DefaultBookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande");
		
		assertEquals("/java/123-titulo-grande", uri);
	}

	@Test
	public void shouldGeneratePrePostURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");
		
		DefaultBookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkablePrePostURL(123, 456, "titulo grande");
		
		assertEquals("/prepost/123/456/titulo-grande", uri);
	}
	
}

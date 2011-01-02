package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.com.caelum.guj.view.Slugger;

public class DefaultBookmarkableURIBuilderTest {

	@Test
	public void shouldGeneratePaginatedURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande", 3);

		assertEquals("/java/123-titulo-grande/3", uri);
	}

	@Test
	public void shouldGeneratePaginatedURIWithAnchor() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande", 3, "456");

		assertEquals("/java/123-titulo-grande/3#456", uri);
	}

	@Test
	public void shouldGenerateSimpleURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableURL(123, "titulo grande");

		assertEquals("/java/123-titulo-grande", uri);
	}

	@Test
	public void shouldGeneratePrePostURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkablePrePostURL(123, 456, "titulo grande");

		assertEquals("/prepost/123/456/titulo-grande", uri);
	}

	@Test
	public void shouldGenerateCompletePrePostURIFromACompleteURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableCompletePrePostURL("http://www.guj.com.br/posts/preList/2/32.java",
				"titulo grande");

		assertEquals("http://www.guj.com.br/prepost/2/32/titulo-grande", uri);
	}

	@Test
	public void shouldReturnTheOriginalCompleteURIWhenItsNotAPrePostURI() {
		Slugger slugger = mock(Slugger.class);
		when(slugger.sluggerize("titulo grande")).thenReturn("titulo-grande");

		BookmarkableURIBuilder builder = new DefaultBookmarkableURIBuilder(slugger);
		String uri = builder.bookmarkableCompletePrePostURL("http://www.guj.com.br/posts/list/2.java", "titulo grande");

		assertEquals("http://www.guj.com.br/posts/list/2.java", uri);
	}

}

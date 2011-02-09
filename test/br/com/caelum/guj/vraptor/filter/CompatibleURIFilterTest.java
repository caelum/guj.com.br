package br.com.caelum.guj.vraptor.filter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompatibleURIFilterTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private FilterChain chain;
	@Mock
	private FilterConfig filterConfig;

	private CompatibleURIFilter filter;

	@Before
	public void setup() throws ServletException {
		filter = new CompatibleURIFilter();
		when(filterConfig.getInitParameter("topicRepository")).thenReturn(
				TopicRepositoryStub.class.getName());
		
		filter.init(filterConfig);
	}

	@After
	public void tearDown() {
		filter.destroy();
	}

	@Test
	public void shouldInvokeDoFilterIfURIIsBookmarkable() throws Exception {
		String requestedURI = "guj.com.br/java/20-erich-created-jforum";

		when(request.getRequestURI()).thenReturn(requestedURI);
		when(request.getContextPath()).thenReturn("guj.com.br");

		filter.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}

	@Test
	public void shouldRedirectToBookmarkableURIIfCompatibleURI() throws IOException, ServletException {
		String requestedURI = "guj.com.br/posts/list/20.java";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";

		when(request.getRequestURI()).thenReturn(requestedURI);
		when(request.getContextPath()).thenReturn("guj.com.br");

		filter.doFilter(request, response, chain);
		
		verify(chain,never()).doFilter(request, response);
		verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response).setHeader("Location", bookmarkableURI);
	}

	@Test
	public void shouldRestoreOriginalBookmarkableURIIfUserHasAlteredTheURI() throws IOException, ServletException {
		String originalURI = "guj.com.br/java/20-erich-created-jforum";
		String alteredURI = "guj.com.br/java/20-sr-saude-actually-created-jforum";

		when(request.getRequestURI()).thenReturn(alteredURI);
		when(request.getContextPath()).thenReturn("guj.com.br");
		
		filter.doFilter(request, response, chain);

		verify(chain,never()).doFilter(request, response);
		verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response).setHeader("Location", originalURI);
	}

}

package br.com.caelum.guj.vraptor.filter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
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
public class BookmarkableURIFilterTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private FilterChain chain;
	@Mock
	private RequestDispatcher requestDispatcher;

	private BookmarkableURIFilter filter;
	
	@Before
	public void setup() throws ServletException {
		filter = new BookmarkableURIFilter();
		
		when(request.getContextPath()).thenReturn("guj.com.br");
	}

	@After
	public void tearDown() {
	}

	
	@Test
	public void shouldInvokeDoFilterIfRequestURLIsNotToForum() throws Exception {
		String requestedURI = "guj.com.br/rss/recentTopics.java";

		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}
	
	@Test
	public void shouldInvokeDoFilterIfURIIsBookmarkableAndCorrect() throws Exception {
		String requestedURI = "guj.com.br/java/20-erich-created-jforum";
		String compatibleURI = "/posts/list/20.java";

		when(request.getRequestURI()).thenReturn(requestedURI);
		when(request.getRequestDispatcher(compatibleURI)).thenReturn(requestDispatcher);

		filter.doFilter(request, response, chain);

		verify(this.request).getRequestDispatcher(compatibleURI);
	}
}

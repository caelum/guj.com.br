package br.com.caelum.guj.vraptor.filter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
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
	@Mock
	private FilterConfig filterConfig;
	@Mock
	private ServletContext servletContext;
	
	private BookmarkableURIFilter filter;
	
	private URICacheStub uriCacheStub;
	
	@Before
	public void setup() throws ServletException {
		filter = new BookmarkableURIFilter();
		uriCacheStub = new URICacheStub();
		
		when(filterConfig.getInitParameter("topicRepository")).thenReturn(
				TopicRepositoryStub.class.getName());
		
		when(filterConfig.getServletContext()).thenReturn(servletContext);
		when(filterConfig.getServletContext().getAttribute("URICache")).thenReturn(uriCacheStub);
		
		when(request.getContextPath()).thenReturn("guj.com.br");
		
		filter.init(filterConfig);
	}

	@After
	public void tearDown() {
		filter.destroy();
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
	
	@Test
	public void shouldRedirectToCachedIfURIIsShortBookmarkableURI() throws Exception {
		String requestedURI = "guj.com.br/java/20";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";
		
		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);
		
		verify(chain,never()).doFilter(request, response);
		verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response).setHeader("Location", bookmarkableURI);
	}
	
	@Test
	public void shouldRedirectToCachedIfURIIsShortBookmarkableURIAndCached() throws Exception {
		String requestedURI = "guj.com.br/java/20";
		String compatibleURI = "guj.com.br/posts/list/20.java";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";
		
		this.uriCacheStub.put(compatibleURI, bookmarkableURI);
		
		when(request.getRequestURI()).thenReturn(requestedURI);
		
		filter.doFilter(request, response, chain);
		
		Assert.assertTrue(uriCacheStub.isGetBookmarkableURICalled());
		verify(chain,never()).doFilter(request, response);
		verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response).setHeader("Location", bookmarkableURI);
	}
	
	@Test
	public void shouldCacheBookmarkableURIConvertion() throws Exception{
		String requestedURI = "guj.com.br/java/20";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";

		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		
		TopicRepositoryStub repository = getTopicRepositoryFromFilter(filter);
		
		Assert.assertEquals(1, repository.getCallsToRepository());
		
		verify(chain,never()).doFilter(request, response);
		verify(response, times(3)).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response, times(3)).setHeader("Location", bookmarkableURI);
	}
	
	private TopicRepositoryStub getTopicRepositoryFromFilter(BookmarkableURIFilter filter) throws NoSuchFieldException, IllegalAccessException {
		Field topicRepositoryField = BookmarkableURIFilter.class.getDeclaredField("topicRepository");
		topicRepositoryField.setAccessible(true);
		TopicRepositoryStub repository = (TopicRepositoryStub) topicRepositoryField.get(filter);
		return repository;
	}
}

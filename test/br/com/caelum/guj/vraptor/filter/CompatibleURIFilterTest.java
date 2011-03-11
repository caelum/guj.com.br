package br.com.caelum.guj.vraptor.filter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

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
	@Mock
	private ServletContext servletContext;

	private CompatibleURIFilter filter;
	
	private URICacheStub uriCacheStub;

	@Before
	public void setup() throws ServletException {
		filter = new CompatibleURIFilter();
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

		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);

		verify(chain).doFilter(request, response);
	}
	
	@Test
	public void shouldInvokeDoFilterIfURIIsBookmarkableAndCorrectUsingCacheIfCached() throws Exception {
		String requestedURI = "guj.com.br/java/20-erich-created-jforum";
		
		when(request.getRequestURI()).thenReturn(requestedURI);
		TopicRepositoryStub repository = getTopicRepositoryFromFilter(filter);

		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		
		Assert.assertEquals(0, repository.getCallsToRepository());
		verify(chain, times(4)).doFilter(request, response);
	}

	@Test
	public void shouldRedirectToBookmarkableURIIfRequestURIIsCompatibleURI() throws IOException, ServletException {
		String requestedURI = "guj.com.br/posts/list/20.java";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";

		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);
		
		verify(chain,never()).doFilter(request, response);
		verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response).setHeader("Location", bookmarkableURI);
	}
	
	@Test
	public void shouldCacheBookmarkableURIConvertion() throws Exception{
		String requestedURI = "guj.com.br/posts/list/20.java";
		String bookmarkableURI = "guj.com.br/java/20-erich-created-jforum";

		when(request.getRequestURI()).thenReturn(requestedURI);

		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		filter.doFilter(request, response, chain);
		
		TopicRepositoryStub repository = getTopicRepositoryFromFilter(filter);
		
		Assert.assertEquals(bookmarkableURI, uriCacheStub.getBookmarkableURI(requestedURI));
		Assert.assertTrue(uriCacheStub.isGetBookmarkableURICalled());
		Assert.assertEquals(1, repository.getCallsToRepository());
		
		verify(chain,never()).doFilter(request, response);
		verify(response, times(3)).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		verify(response, times(3)).setHeader("Location", bookmarkableURI);
	}
	
	@Test
	public void shouldInvokeDoFilterIfBookmarkableURIChanged() throws Exception{
		String originalURI = "guj.com.br/java/20-erich-created-jforum";
		String alteredURI = "guj.com.br/java/20-sr-saude-actually-created-jforum";
		
		TopicRepositoryStub repository = getTopicRepositoryFromFilter(filter);
		
		when(request.getRequestURI()).thenReturn(originalURI);
		filter.doFilter(request, response, chain);
		
		Assert.assertFalse(uriCacheStub.isPutCalled());
		
		when(request.getRequestURI()).thenReturn(alteredURI);
		filter.doFilter(request, response, chain);
		
		verify(chain, times(2)).doFilter(request, response);
		Assert.assertEquals(0, repository.getCallsToRepository());
	}

	private TopicRepositoryStub getTopicRepositoryFromFilter(CompatibleURIFilter filter) throws NoSuchFieldException, IllegalAccessException {
		Field topicRepositoryField = CompatibleURIFilter.class.getDeclaredField("topicRepository");
		topicRepositoryField.setAccessible(true);
		TopicRepositoryStub repository = (TopicRepositoryStub) topicRepositoryField.get(filter);
		return repository;
	}
}

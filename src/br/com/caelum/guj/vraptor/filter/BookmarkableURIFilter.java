package br.com.caelum.guj.vraptor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.repositories.TopicRepositoryWrapper;
import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.DefaultURICache;
import br.com.caelum.guj.uri.URICache;
import br.com.caelum.guj.uri.bookmarkable.AllBookmarkableToCompatibleConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;
import br.com.caelum.guj.uri.compatible.CompatibleToBookmarkablePostConverter;
import br.com.caelum.guj.view.Slugger;

public class BookmarkableURIFilter implements Filter {
	private URICache cache;
	private TopicRepository topicRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response= (HttpServletResponse) res;

		ConverterMatcher converters = new ConverterMatcher(AllBookmarkableToCompatibleConverters.get(request
				.getRequestURI()));

		
		if (converters.oneMatched()) {
			String compatibleURI = converters.getConverter().convert();

			if (converters.shortBookmarkableURI()) {
				redirectUsingCache(request, response, compatibleURI);
			} else {
				request.setAttribute("bookmarkableURIBeforeForward", request.getRequestURI());
				request.getRequestDispatcher(compatibleURI).forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private void redirectUsingCache(HttpServletRequest request, HttpServletResponse response, String compatibleURI) {
		String cachedBookmarkableURI = cache.getBookmarkableURI(compatibleURI);
		if (cachedBookmarkableURI == null) {
			cachedBookmarkableURI = compatibleURIToBookmarkableURI(compatibleURI, request);
			cache.put(compatibleURI, cachedBookmarkableURI);
		} 
		redirectTo(response, cachedBookmarkableURI);
	}
	
	private String compatibleURIToBookmarkableURI(String compatibleURI, HttpServletRequest request) {
		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(compatibleURI,
				topicRepository, new DefaultBookmarkableURIBuilder(new Slugger()));
		if (converter.isConvertable()) {
			return request.getContextPath() + converter.convert();
		}
		return null;
	}

	private void redirectTo(HttpServletResponse response, String newUri) {
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", newUri);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		createURICache(config);
		createTopicRepository(config);
	}

	private void createURICache(FilterConfig config) throws ServletException {
		URICache cacheFromContext = (URICache) config.getServletContext().getAttribute("URICache");
		if (cacheFromContext == null) {
			cache = new DefaultURICache();
			config.getServletContext().setAttribute("URICache", cache);
		} else {
			this.cache = cacheFromContext;
		}
	}
	
	private void createTopicRepository(FilterConfig config) throws ServletException {
		String topicRepositoryClassName = config.getInitParameter("topicRepository");
		if (topicRepositoryClassName == null) {
			topicRepository = new TopicRepositoryWrapper();
		} else {
			try {
				topicRepository = (TopicRepository) Class.forName(topicRepositoryClassName).newInstance();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
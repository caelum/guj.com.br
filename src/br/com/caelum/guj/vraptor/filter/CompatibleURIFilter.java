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

import org.apache.log4j.Logger;

import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.repositories.TopicRepositoryWrapper;
import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.DefaultURICache;
import br.com.caelum.guj.uri.URICache;
import br.com.caelum.guj.uri.bookmarkable.AllBookmarkableToCompatibleConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;
import br.com.caelum.guj.uri.compatible.CompatibleToBookmarkablePostConverter;
import br.com.caelum.guj.view.Slugger;

public class CompatibleURIFilter implements Filter {
	private static Logger LOG = Logger.getLogger(CompatibleURIFilter.class);
	private TopicRepository topicRepository;
	private URICache cache;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String requestURI = request.getRequestURI();

		String cachedBookmarkableUri = cache.getBookmarkableURI(requestURI);

		if (cachedBookmarkableUri != null) {
			LOG.debug("Using cache to redirect to " + cachedBookmarkableUri);
			redirectTo(response, cachedBookmarkableUri);
			return;
		}

		String newBookmarkableUri = compatibleURIToBookmarkableURI(requestURI, request);

		if (newBookmarkableUri != null) {
			redirectTo(response, newBookmarkableUri);
			cache.put(requestURI, newBookmarkableUri);

			LOG.debug("Caching " + newBookmarkableUri);
			return;
		}

		String compatibleURI = bookmarkableURIToCompatibleURI(requestURI, request);
		
		boolean requestURIIsBookmarkable = compatibleURI != null;

		if (requestURIIsBookmarkable) {
			cachedBookmarkableUri = cache.getBookmarkableURI(compatibleURI);
			
			boolean compatibleURLIsCached = cachedBookmarkableUri != null;
			
			if (compatibleURLIsCached && !requestURI.equals(cachedBookmarkableUri)) {
				redirectTo(response, cachedBookmarkableUri);
				LOG.debug("Using cache to redirect to " + cachedBookmarkableUri);
				return;
			}
			String correctBookmarkableURI = compatibleURIToBookmarkableURI(compatibleURI, request);
			
			cache.put(compatibleURI, correctBookmarkableURI);
			LOG.debug("Caching " + correctBookmarkableURI);
			
			if (!requestURI.equals(correctBookmarkableURI)) {
				redirectTo(response, correctBookmarkableURI);
				return;
			}
		}

		chain.doFilter(req, res);
	}

	private String bookmarkableURIToCompatibleURI(String compatibleURI, HttpServletRequest request) {
		ConverterMatcher converter = new ConverterMatcher(AllBookmarkableToCompatibleConverters.get(compatibleURI));

		if (converter.oneMatched()) {
			return request.getContextPath() + converter.getConverter().convert();
		}
		return null;
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
		cache.removeCache();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		createTopicRepository(config);
		createURICache(config);
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
	
	private void createURICache(FilterConfig config) throws ServletException {
		String cacheClassName = config.getInitParameter("cache");
		if (cacheClassName == null) {
			cache = new DefaultURICache();
		} else {
			try {
				cache = (URICache) Class.forName(cacheClassName).newInstance();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}

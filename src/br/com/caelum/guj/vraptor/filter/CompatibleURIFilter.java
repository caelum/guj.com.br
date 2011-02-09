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

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.repositories.TopicRepositoryWrapper;
import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.bookmarkable.AllBookmarkableToCompatibleConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;
import br.com.caelum.guj.uri.compatible.CompatibleToBookmarkablePostConverter;
import br.com.caelum.guj.view.Slugger;

public class CompatibleURIFilter implements Filter {

	private static final String URIS = "uris";
	private static Logger LOG = Logger.getLogger(CompatibleURIFilter.class);
	private Cache compatibleURI_bookmarkableURI;
	private CacheManager cacheManager;
	private TopicRepository topicRepository;

	private String retrieveBookmarkableURIFromCache(String compatibleURI) {
		Element cachedElement = compatibleURI_bookmarkableURI.get(compatibleURI);
		if (cachedElement == null) {
			return null;
		}
		return (String) cachedElement.getValue();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String requestURI = request.getRequestURI();

		String cachedBookmarkableUri = retrieveBookmarkableURIFromCache(requestURI);

		if (cachedBookmarkableUri != null) {
			LOG.debug("Using cache to redirect to " + cachedBookmarkableUri);
			redirectTo(response, cachedBookmarkableUri);
			return;
		}

		String newBookmarkableUri = compatibleToBookmarkableURI(requestURI, request);

		if (newBookmarkableUri != null ) {
			redirectTo(response, newBookmarkableUri);
			compatibleURI_bookmarkableURI.put(new Element(requestURI, newBookmarkableUri));

			LOG.debug("Caching " + newBookmarkableUri);
			return;
		}

		ConverterMatcher allBookmarkableConverters = new ConverterMatcher(
				AllBookmarkableToCompatibleConverters.get(requestURI));

		if (allBookmarkableConverters.oneMatched()) {
			String compatibleURI = allBookmarkableConverters.getConverter().convert();

			String cachedBookmarkableURI = retrieveBookmarkableURIFromCache(compatibleURI);

			if (cachedBookmarkableURI != null && !requestURI.equals(cachedBookmarkableURI)) {
				redirectTo(response, cachedBookmarkableURI);
				return;
			} else {
				String newUri = compatibleToBookmarkableURI(compatibleURI, request);
				if (!newUri.equals(requestURI)) {
					redirectTo(response, newUri);
					compatibleURI_bookmarkableURI.put(new Element(compatibleURI, newUri));
					
					LOG.debug("Caching " + newUri);
					return;
				}
			}
		}
		chain.doFilter(req, res);
	}

	private String compatibleToBookmarkableURI(String compatibleURI, HttpServletRequest request) {
		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(compatibleURI, topicRepository,
				new DefaultBookmarkableURIBuilder(new Slugger()));
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
		cacheManager.removeCache(URIS);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		createTopicRepository(config);
		cacheManager = CacheManager.create();
		compatibleURI_bookmarkableURI = new Cache(URIS, 10000, false, true, 1000000, 1000000);
		cacheManager.addCache(compatibleURI_bookmarkableURI);
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

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

import br.com.caelum.guj.repositories.TopicRepositoryWrapper;
import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.compatible.CompatibleToBookmarkablePostConverter;
import br.com.caelum.guj.view.Slugger;

public class CompatibleURIFilter implements Filter {

	private static Logger LOG = Logger.getLogger(CompatibleURIFilter.class);
	private Cache cache;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		Element cachedElement = cache.get(request.getRequestURI());
		if(cachedElement != null) {
			String cachedUri = (String)cachedElement.getValue();
			LOG.info("Using cache to redirect to " + cachedUri);
			redirectTo(response, cachedUri);
			return;
		}
		
		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(
				request.getRequestURI(), new TopicRepositoryWrapper(),
				new DefaultBookmarkableURIBuilder(new Slugger()));

		LOG.info("compatible filter: " + request.getRequestURI() + " -- and is convertable: "
				+ converter.isConvertable());
		if (converter.isConvertable()) {
			String newUri = request.getContextPath() + converter.convert();
			redirectTo(response, newUri);
			cache.put(new Element(request.getRequestURI(), newUri));
			
			LOG.info("Caching " + newUri);
		} else {
			chain.doFilter(req, res);
		}
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
		CacheManager cacheManager = CacheManager.create();
		cache = new Cache("uris", 10000, false, true, 1000000, 1000000);
		cacheManager.addCache(cache);
	}
}

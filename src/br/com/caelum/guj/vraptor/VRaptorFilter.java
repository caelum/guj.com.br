package br.com.caelum.guj.vraptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.caelum.guj.uri.BookmarkableURIBuilder;
import br.com.caelum.vraptor.VRaptor;

/**
 * Para ignorar as URIs do JForum
 * 
 * @author Lucas Cavalcanti
 * 
 */
public class VRaptorFilter extends VRaptor {
	private static final Logger LOG = Logger.getLogger(VRaptorFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String uri = request.getRequestURI();

		BookmarkableURIBuilder builder = new BookmarkableURIBuilder(uri,
				request.getParameter("page"));
		LOG.info("URL: " + uri + " ------ " + builder.isBookmarkable());
		if (builder.isBookmarkable()) {
			RequestDispatcher rd = request.getRequestDispatcher(builder.buildCompatibleURI());
			LOG.info("Redirection to the compatible url for " + uri + " -- "
					+ builder.buildCompatibleURI());
			rd.forward(request, res);
		} else {
			if (uri.endsWith(".java") || uri.endsWith(".guj")) {
				chain.doFilter(req, res);
			} else {
				super.doFilter(req, res, chain);
			}
		}
	}
}

package br.com.caelum.guj.vraptor;

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

import br.com.caelum.guj.repositories.TopicRepositoryWrapper;
import br.com.caelum.guj.uri.HttpRequestInfo;
import br.com.caelum.guj.uri.compatible.CompatibleToBookmarkablePostConverter;
import br.com.caelum.guj.view.Slugger;

public class CompatibleURIFilter implements Filter {

	private static Logger LOG = Logger.getLogger(CompatibleURIFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(
				new HttpRequestInfo(request), new TopicRepositoryWrapper(), new Slugger());

		LOG.info("compatible filter: " + request.getRequestURI() + " -- and is convertable: "
				+ converter.isConvertable());
		if (converter.isConvertable()) {
			response.sendRedirect(request.getContextPath() + converter.convert());
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}

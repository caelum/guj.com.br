package br.com.caelum.guj.vraptor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GUJSessionCleanupFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpSession session = ((HttpServletRequest) req).getSession();
		session.removeAttribute("newsletterParticipant");
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
	}

}

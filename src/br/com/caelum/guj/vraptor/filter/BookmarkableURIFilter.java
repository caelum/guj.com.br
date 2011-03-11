package br.com.caelum.guj.vraptor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.guj.uri.bookmarkable.AllBookmarkableToCompatibleConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;

public class BookmarkableURIFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		ConverterMatcher converters = new ConverterMatcher(AllBookmarkableToCompatibleConverters.get(request
				.getRequestURI()));

		if (converters.oneMatched()) {
			dispatcher(request, converters).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private RequestDispatcher dispatcher(HttpServletRequest request, ConverterMatcher converters) {
		return request.getRequestDispatcher(converters.getConverter()
				.convert());
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
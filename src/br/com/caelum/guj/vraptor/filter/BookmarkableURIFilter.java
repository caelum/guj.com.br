package br.com.caelum.guj.vraptor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.jforum.JForumExecutionContext;
import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.DefaultCompatibleURIBuilder;
import br.com.caelum.guj.uri.bookmarkable.AllConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;
import br.com.caelum.guj.view.Slugger;
import br.com.caelum.vraptor.VRaptor;
import freemarker.template.SimpleHash;

public class BookmarkableURIFilter extends VRaptor {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		this.registerSlugger();

		ConverterMatcher allConverters = new ConverterMatcher(AllConverters.get(request
				.getRequestURI()));

		if (allConverters.oneMatched()) {
			RequestDispatcher rd = request.getRequestDispatcher(allConverters.getConverter()
					.convert());

			rd.forward(request, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	private void registerSlugger() {
		if (!JForumExecutionContext.exists()) {
			JForumExecutionContext ctx = JForumExecutionContext.get();
			JForumExecutionContext.set(ctx);
		}
		SimpleHash templateCtx = JForumExecutionContext.getTemplateContext();
		templateCtx.put("compatibleUriBuilder", new DefaultCompatibleURIBuilder());
		templateCtx.put("bookmarkableUriBuilder", new DefaultBookmarkableURIBuilder(new Slugger()));
	}

}
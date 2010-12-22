package br.com.caelum.guj.vraptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.jforum.JForumExecutionContext;

import org.apache.log4j.Logger;

import br.com.caelum.guj.uri.HttpRequestInfo;
import br.com.caelum.guj.uri.bookmarkable.AllConverters;
import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;
import br.com.caelum.guj.view.Slugger;
import br.com.caelum.vraptor.VRaptor;
import freemarker.template.SimpleHash;

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
		this.registerSlugger();
		String uri = request.getRequestURI();

		ConverterMatcher allConverters = new ConverterMatcher(
				AllConverters.get(new HttpRequestInfo(request)));

		if (allConverters.oneMatched()) {
			RequestDispatcher rd = request.getRequestDispatcher(allConverters.getConverter()
					.convert());
			LOG.info("Redirection to the compatible url for " + uri + " -- "
					+ allConverters.getConverter().convert());

			rd.forward(request, res);
		} else {
			if (uri.endsWith(".java") || uri.endsWith(".guj")) {
				chain.doFilter(req, res);
			} else {
				super.doFilter(req, res, chain);
			}
		}
	}

	private void registerSlugger() {
		if (!JForumExecutionContext.exists()) {
			JForumExecutionContext ctx = JForumExecutionContext.get();
			JForumExecutionContext.set(ctx);
		}
		SimpleHash templateCtx = JForumExecutionContext.getTemplateContext();
		templateCtx.put("slugger", new Slugger());
	}

}
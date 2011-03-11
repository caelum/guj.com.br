package br.com.caelum.guj.vraptor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.jforum.JForumExecutionContext;
import freemarker.template.SimpleHash;

import br.com.caelum.guj.uri.DefaultBookmarkableURIBuilder;
import br.com.caelum.guj.uri.DefaultCompatibleURIBuilder;
import br.com.caelum.guj.view.Slugger;
import br.com.caelum.vraptor.VRaptor;

/**
 * Para ignorar as URIs do JForum
 * 
 * @author Lucas Cavalcanti
 * 
 */
public class VRaptorFilter extends VRaptor {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		this.registerSlugger();

		HttpServletRequest request = (HttpServletRequest) req;
		String uri = request.getRequestURI();

		if (uri.endsWith(".java") || uri.endsWith(".guj")) {
			chain.doFilter(req, res);
		} else {
			super.doFilter(req, res, chain);
		}

	}
	
	private void registerSlugger() {
		if (!JForumExecutionContext.exists()) {
			JForumExecutionContext ctx = JForumExecutionContext.get();
			JForumExecutionContext.set(ctx);
		}
		SimpleHash context = JForumExecutionContext.getTemplateContext();
		context.put("compatibleUriBuilder", new DefaultCompatibleURIBuilder());
		context.put("bookmarkableUriBuilder", new DefaultBookmarkableURIBuilder(new Slugger()));
	}
}

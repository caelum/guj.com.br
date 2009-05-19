package br.com.guj;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jforum.JForumExecutionContext;
import net.jforum.SessionFacade;
import net.jforum.context.JForumContext;
import net.jforum.context.RequestContext;
import net.jforum.context.ResponseContext;
import net.jforum.context.web.WebRequestContext;
import net.jforum.context.web.WebResponseContext;
import net.jforum.entities.UserSession;
import net.jforum.util.preferences.ConfigKeys;

import org.vraptor.VRaptorServlet;

import br.com.guj.feeds.Agregator;
import br.com.guj.feeds.JobsAgregator;

public class GUJServlet extends VRaptorServlet {
	private Agregator forumAgregator;
	private Agregator newsAgregator;
	private Agregator infoqAgregator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Config.loadConfigs();

		this.forumAgregator = new Agregator("forum.refresh.interval", "forum.items", "forum.url");
		this.newsAgregator = new Agregator("news.refresh.interval", "news.items", "news.url");
		this.infoqAgregator = new Agregator("infoq.refresh.interval", "infoq.items", "infoq.url");

		JobsAgregator.start();
	}

	/**
	 * @see org.vraptor.VRaptorServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("infoq", this.infoqAgregator.getItems());
		request.setAttribute("news", this.newsAgregator.getItems());
		request.setAttribute("forum", this.forumAgregator.getItems());

		boolean isLogged = "1".equals(request.getSession().getAttribute(ConfigKeys.LOGGED));
		request.setAttribute("logged", isLogged);

		try {
			JForumExecutionContext ex = JForumExecutionContext.get();

			RequestContext requestContext = new WebRequestContext(request);
			ResponseContext responseContext = new WebResponseContext(response);

			JForumContext forumContext = new JForumContext(request.getContextPath(),
                ".java", requestContext, responseContext);

            ex.setForumContext(forumContext);

            JForumExecutionContext.set(ex);

			if (isLogged) {
				UserSession userSession = SessionFacade.getUserSession(request.getSession().getId());
				request.setAttribute("userSession", userSession);
			}

			super.service(request, response);
		}
		finally {
			JForumExecutionContext.finish();
		}
	}
}

package br.com.caelum.guj.controller;

import java.util.ArrayList;
import java.util.List;

import net.jforum.dao.generic.GenericTopicDAO;
import br.com.caelum.guj.feeds.TopicJsonFeed;
import br.com.caelum.guj.uri.BookmarkableURIBuilder;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class JsonFeedController {
	private static final int INTERNAL_FORUM = 16;
	private static final int MAX_RESULTS = 20;
	private final Result result;
	private final BookmarkableURIBuilder uriBuilder;

	public JsonFeedController(Result result, BookmarkableURIBuilder uriBuilder) {
		this.result = result;
		this.uriBuilder = uriBuilder;
	}

	@Path("jsonfeed/forum/{forumId}")
	public void topics(Integer forumId) {
		List<TopicJsonFeed> topics = new ArrayList<TopicJsonFeed>();

		if (forumId != INTERNAL_FORUM) {
			GenericTopicDAO dao = new GenericTopicDAO();
			topics = dao.selectRecentFromForum(forumId, MAX_RESULTS, uriBuilder);
		}
		
		result.use(Results.json()).withoutRoot().from(topics).recursive().serialize();
	}
}

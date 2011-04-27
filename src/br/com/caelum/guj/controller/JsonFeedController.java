package br.com.caelum.guj.controller;

import java.util.List;

import net.jforum.dao.generic.GenericTopicDAO;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class JsonFeedController {
	private static final int FIRST_RESULT = 0;
	private static final int MAX_RESULTS = 30;
	private final Result result;

	public JsonFeedController(Result result) {
		this.result = result;
	}

	@SuppressWarnings("rawtypes")
	@Path("jsonfeed/forum/{forumId}")
	public void topics(Integer forumId) {
		GenericTopicDAO dao = new GenericTopicDAO();
		List topics = dao.selectAllByForumByLimit(forumId, FIRST_RESULT, MAX_RESULTS);
		
		result.use(Results.json()).withoutRoot().from(topics).recursive().serialize();
//		result.include("topics", topics);
	}
}

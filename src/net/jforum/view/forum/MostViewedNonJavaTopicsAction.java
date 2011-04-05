package net.jforum.view.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.jforum.Command;
import net.jforum.JForumExecutionContext;
import net.jforum.dao.DataAccessDriver;
import net.jforum.dao.UserDAO;
import net.jforum.dao.generic.GenericTopicDAO;
import net.jforum.entities.Forum;
import net.jforum.entities.Topic;
import net.jforum.entities.User;
import net.jforum.repository.ForumRepository;
import net.jforum.util.I18n;
import net.jforum.util.preferences.ConfigKeys;
import net.jforum.util.preferences.SystemGlobals;
import net.jforum.util.preferences.TemplateKeys;
import net.jforum.view.forum.common.TopicsCommon;
import net.jforum.view.forum.common.ViewCommon;

public class MostViewedNonJavaTopicsAction  extends Command {
	private static final int JAVA_FORUM_ID = 17;
	private static final int OFF_TOPIC_ID = 2;
	private static final int QUERY_LIMIT = 5;
	private List forums;

	public void list()
	{
		int postsPerPage = SystemGlobals.getIntValue(ConfigKeys.POSTS_PER_PAGE);

		this.setTemplateName(TemplateKeys.HOTTEST_LIST);
		
		this.context.put("postsPerPage", new Integer(postsPerPage));
		this.context.put("topics", this.topics());
		this.context.put("forums", this.forums);
		this.context.put("pageTitle", I18n.getMessage("ForumBase.hottestTopics"));

		TopicsCommon.topicListingBase();
		this.request.setAttribute("template", null);
	}
	
	List topics()
	{
		int postsPerPage = SystemGlobals.getIntValue(ConfigKeys.POSTS_PER_PAGE);
		List idsExcludedFromSearch = new ArrayList();
		idsExcludedFromSearch.add(this.JAVA_FORUM_ID);
		idsExcludedFromSearch.add(this.OFF_TOPIC_ID);
		
		List mostViewedTopics = new GenericTopicDAO().selectMostViewedNotFromForums(idsExcludedFromSearch, QUERY_LIMIT);
		
		this.forums = new ArrayList(postsPerPage);

		for (Iterator iter = mostViewedTopics.iterator(); iter.hasNext(); ) {
			Topic t = (Topic)iter.next();
			
			if (TopicsCommon.isTopicAccessible(t.getForumId())) {
				// Get name of forum that the topic refers to
				Forum f = ForumRepository.getForum(t.getForumId());
				forums.add(f);
			}
			else {
				iter.remove();
			}
		}
		
		JForumExecutionContext.getRequest().removeAttribute("template");
		
		return TopicsCommon.prepareTopics(mostViewedTopics);
	}

	public void showTopicsByUser() 
	{
		DataAccessDriver da = DataAccessDriver.getInstance();
		
		UserDAO udao = da.newUserDAO();
		User u = udao.selectById(this.request.getIntParameter("user_id"));
		
		if (u.getId() == 0) {
			this.context.put("message", I18n.getMessage("User.notFound"));
			this.setTemplateName(TemplateKeys.USER_NOT_FOUND);
			return;
		} 
			
		TopicsCommon.topicListingBase();
		
		int start = ViewCommon.getStartPage();
		int topicsPerPage = SystemGlobals.getIntValue(ConfigKeys.TOPICS_PER_PAGE);
		int postsPerPage = SystemGlobals.getIntValue(ConfigKeys.POSTS_PER_PAGE);
		
		this.setTemplateName(TemplateKeys.HOTTEST_USER_TOPICS_SHOW);
		
		int totalTopics = da.newTopicDAO().countUserTopics(u.getId());
		
		this.context.put("u", u);
		this.context.put("pageTitle", I18n.getMessage("ForumListing.userTopics") + " " + u.getUsername());
		
		this.context.put("postsPerPage", new Integer(postsPerPage));
		
		List topics = da.newTopicDAO().selectByUserByLimit(u.getId(),start,topicsPerPage);
		
		List l = TopicsCommon.prepareTopics(topics);
		Map forums = new HashMap();
		
		for (Iterator iter = l.iterator(); iter.hasNext(); ) {
			Topic t = (Topic)iter.next();
			
			Forum f = ForumRepository.getForum(t.getForumId());
			
			if (f == null) {
				iter.remove();
				totalTopics--;
				continue;
			}
			
			forums.put(new Integer(t.getForumId()), f);
		}
		
		this.context.put("topics", l);
		this.context.put("forums", forums);
		
		ViewCommon.contextToPagination(start, totalTopics, topicsPerPage);
	}

}

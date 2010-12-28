package br.com.caelum.guj.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.guj.Config;
import br.com.caelum.guj.model.Article;
import br.com.caelum.guj.model.Post;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HomeController {
	private final Result result;
	private final Session session;

	public HomeController(Result result, Session session) {
		this.result = result;
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	private List<Post> getAllPosts() {
		return this.session.createQuery("from Post p order by p.date desc")
				.setMaxResults(Config.getIntvalue("posts.home.items")).setCacheable(true).setCacheRegion("homePosts")
				.list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {
		return this.session.createQuery("from Article a order by a.id desc")
				.setMaxResults(Config.getIntvalue("article.home.items")).setCacheable(true)
				.setCacheRegion("homeArticles").list();
	}

	@Path("/")
	public void index() {
		this.result.include("posts", this.getAllPosts());
		this.result.include("articles", this.getAllArticles());
	}

}

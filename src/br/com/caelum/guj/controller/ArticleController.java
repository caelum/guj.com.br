package br.com.caelum.guj.controller;

import static br.com.caelum.vraptor.view.Results.nothing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.jforum.util.preferences.ConfigKeys;

import org.hibernate.Session;

import br.com.caelum.guj.model.Article;
import br.com.caelum.guj.model.Category;
import br.com.caelum.guj.model.Tag;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ArticleController {

	private final boolean isLogged;

	private final Result result;

	private final Session session;

	public ArticleController(HttpSession httpSession, Result result, Session session) {
		this.result = result;
		this.session = session;
		this.isLogged = "1".equals(httpSession.getAttribute(ConfigKeys.LOGGED));
	}

	@SuppressWarnings("unchecked")
	protected List<Category> getAllCategories() {
		return session.createQuery(
				"from Category c ORDER BY c.name").setCacheable(true)
				.setCacheRegion("Categories").list();
	}

	private Article getArticle(long id) {
		return (Article) session.get(Article.class, id);
	}

	@Path("/articles/{articleId}/tags") @br.com.caelum.vraptor.Post
	public void addTag(long articleId, String tags) {
		if (this.isLogged) {
			List<Tag> newTags = new ArrayList<Tag>();
			String[] p = tags.split(",");

			for (String tagName : p) {
				tagName = tagName.trim();
				Tag tag = this.findTagByName(tagName);

				if (tag == null) {
					tag = new Tag();
					tag.setName(tagName);
				}

				newTags.add(tag);
			}

			Article article = (Article) session.get(Article.class, articleId);
			newTags.removeAll(article.getTags());
			article.getTags().addAll(newTags);
		}
		result.use(nothing());
	}

	private Tag findTagByName(String tag) {
		return (Tag) session.createQuery(
				"from Tag t where lower(t.name) = lower(:name)").setParameter(
				"name", tag).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Path("/articles/{tagName}") @Get
	public void listByTag(String tagName) {
		Tag tag = this.findTagByName(tagName);

		List<Article> articles;
		if (tag == null) {
			tag = new Tag();
			tag.setName(tagName);
			articles = new ArrayList<Article>();
		} else {
			articles = session.createQuery(
					"select a from Article a join a.tags t where t = :tag")
					.setParameter("tag", tag).list();
		}
		result.include("tag", tag);
		result.include("articles", articles);
	}

	@Path("/articles") @Get
	public void list() {
		result.include("categories", this.getAllCategories());
	}

	@Path("/articles/{id}")
	public void show(long id) {
		result.include("article", this.getArticle(id));
	}

}

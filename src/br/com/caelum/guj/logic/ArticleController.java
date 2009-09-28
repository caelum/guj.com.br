package br.com.caelum.guj.logic;

import static br.com.caelum.vraptor.view.Results.nothing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.jforum.util.preferences.ConfigKeys;
import br.com.caelum.guj.hibernate.HibernateUtil;
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

	public ArticleController(HttpSession session, Result result) {
		this.result = result;
		this.isLogged = "1".equals(session.getAttribute(ConfigKeys.LOGGED));
	}

	@SuppressWarnings("unchecked")
	protected List<Category> getAllCategories() {
		return HibernateUtil.getSession().createQuery(
				"from Category c ORDER BY c.name").setCacheable(true)
				.setCacheRegion("Categories").list();
	}

	private Article getArticle(long id) {
		return (Article) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Article.class, id);
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

			Article article = (Article) HibernateUtil.getSession().get(
					Article.class, articleId);
			newTags.removeAll(article.getTags());
			article.getTags().addAll(newTags);
		}
		result.use(nothing());
	}

	private Tag findTagByName(String tag) {
		return (Tag) HibernateUtil.getSession().createQuery(
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
			articles = HibernateUtil.getSession().createQuery(
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

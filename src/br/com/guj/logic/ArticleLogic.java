package br.com.guj.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.jforum.util.preferences.ConfigKeys;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.InterceptedBy;
import org.vraptor.annotations.Out;
import org.vraptor.annotations.Parameter;
import org.vraptor.annotations.Viewless;
import org.vraptor.interceptor.MultipartRequestInterceptor;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.Category;
import br.com.guj.model.Post;
import br.com.guj.model.Tag;

@Component("article")
@InterceptedBy(MultipartRequestInterceptor.class)
public class ArticleLogic {

	private List<Post> postsBox;
	private List<Article> articlesBox;
	private List<Category> categories;
	private List<Article> articles;
	private List<Article> pendingArticles;
	private Tag tag;
	private boolean isLogged;
	private Article article;

	@Out
	private boolean isModerator;

	@Out
	private boolean isAuthor;

	@Out
	private List<String> linksOfCodes;

	public ArticleLogic(HttpSession session) {
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

	@Viewless
	public void addTag(@Parameter(key = "articleId") long articleId,
			@Parameter(key = "tags") String tags) {
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
	}

	private Tag findTagByName(String tag) {
		return (Tag) HibernateUtil.getSession().createQuery(
				"from Tag t where lower(t.name) = lower(:name)").setParameter(
				"name", tag).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public void listByTag(@Parameter(key = "tag") String tagName) {
		Tag tag = this.findTagByName(tagName);

		if (tag == null) {
			this.tag = new Tag();
			this.tag.setName(tagName);
			this.articles = new ArrayList<Article>();
		} else {
			this.tag = tag;
			this.articles = HibernateUtil.getSession().createQuery(
					"select a from Article a join a.tags t where t = :tag")
					.setParameter("tag", tag).list();
		}
	}

	public void list() {
		this.categories = this.getAllCategories();
	}

	public void show(@Parameter(key = "id") long id) {
		this.article = this.getArticle(id);
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public Article getArticle() {
		return article;
	}

	public List<Post> getPostsBox() {
		return postsBox;
	}

	public List<Article> getArticlesBox() {
		return articlesBox;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Tag getTag() {
		return tag;
	}

	public boolean isModerator() {
		return isModerator;
	}

	public void setModerator(boolean isModerator) {
		this.isModerator = isModerator;
	}

	public boolean isAuthor() {
		return isAuthor;
	}

	public void setAuthor(boolean isAuthor) {
		this.isAuthor = isAuthor;
	}

	public List<Article> getPendingArticles() {
		return pendingArticles;
	}

	public void setPendingArticles(List<Article> pendingArticles) {
		this.pendingArticles = pendingArticles;
	}

	public List<String> getLinksOfCodes() {
		return linksOfCodes;
	}

	public void setLinksOfCodes(List<String> linksOfCodes) {
		this.linksOfCodes = linksOfCodes;
	}

}

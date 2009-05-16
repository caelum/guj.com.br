package br.com.guj.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.In;
import org.vraptor.annotations.Out;
import org.vraptor.annotations.Parameter;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.ArticleLevel;
import br.com.guj.model.Category;

@Component("approve")
public class ApproveLogic {

	private List<Article> articles;

	private List<Category> categories;

	@In
	private HttpServletRequest request;

	@Out
	private boolean isModerator;

	@SuppressWarnings("unchecked")
	public List<Article> getAllArticles() {

		return HibernateUtil.getSession().createQuery(
				"from Article a where a.approved = false ORDER BY a.date")
				.setCacheable(true).setCacheRegion("ApproveArticles").list();

	}

	private Article getArticle(long id) {
		return (Article) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Article.class, id);
	}

	public void list() {

		// UserSession us = (UserSession) request.getAttribute("userSession");

		// article.setModeratorId(us.getUserId());

		this.isModerator = true;

		this.articles = this.getAllArticles();

		this.categories = this.getAllCategories();

	}

	@SuppressWarnings("unchecked")
	protected List<Category> getAllCategories() {
		return HibernateUtil.getSession().createQuery(
				"from Category c ORDER BY c.name").setCacheable(false).list();
	}

	public void save(@Parameter(key = "articleId") long articleId,
			@Parameter(key = "categoryId") long categoryId,
			@Parameter(key = "articleLevel") String articleLevel) {

		Article article = this.getArticle(articleId);

		article.setApproved(true);
		article.setLevel(ArticleLevel.valueOf(articleLevel));

		Category category = new CategoryLogic().getCategory(categoryId);
		article.setCategory(category);

		// UserSession us = (UserSession) request.getAttribute("userSession");

		// article.setModeratorId(us.getUserId());

	}

	public void desaproove(@Parameter(key = "id") long articleId) {

		Article article = this.getArticle(articleId);

		article.setApproved(false);

	}

	public void delete(@Parameter(key = "id") long id) {

		Article article = this.getArticle(id);

		HibernateUtil.getSessionFactory().getCurrentSession().delete(article);

	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}

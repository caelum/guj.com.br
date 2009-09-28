package br.com.caelum.guj.logic;

import static br.com.caelum.vraptor.view.Results.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.jforum.entities.UserSession;
import br.com.caelum.guj.hibernate.HibernateUtil;
import br.com.caelum.guj.model.Article;
import br.com.caelum.guj.model.ArticleLevel;
import br.com.caelum.guj.model.Category;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ApproveController {

	private final HttpServletRequest request;
	private final Result result;

	public ApproveController(Result result, HttpServletRequest request) {
		this.result = result;
		this.request = request;
	}

	public void list() {
		result.include("isModerator", true);
		result.include("articles", this.getAllArticles());
		result.include("categories", this.getAllCategories());
	}

	@SuppressWarnings("unchecked")
	protected List<Category> getAllCategories() {

		return HibernateUtil.getSession().createQuery(
				"from Category c ORDER BY c.name").setCacheable(false).list();

	}

	public void save(long articleId, long categoryId, String articleLevel) {

		Article article = this.getArticle(articleId);

		article.setApproved(true);
		article.setLevel(ArticleLevel.valueOf(articleLevel));

		Category category = new CategoryController(result, null).getCategory(categoryId);
		article.setCategory(category);

		result.include("isModerator", true);
		result.use(logic()).forwardTo(ApproveController.class).list();
	}

	// XXX ninguém chama isso
	public void disaproove(long id) {

		Article article = this.getArticle(id);

		article.setApproved(false);

		UserSession us = (UserSession) this.request.getAttribute("userSession");

		article.setModeratorId(us.getUserId());

		result.use(logic()).forwardTo(ArticleLogic.class).list();
	}

	public void delete(long id) {

		Article article = this.getArticle(id);

		HibernateUtil.getSessionFactory().getCurrentSession().delete(article);

		result.use(logic()).forwardTo(ApproveController.class).list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {

		return HibernateUtil.getSession().createQuery(
				"from Article a where a.approved = false ORDER BY a.date")
				.setCacheable(true).setCacheRegion("ApproveArticles").list();

	}

	private Article getArticle(long id) {

		return (Article) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Article.class, id);
	}

}

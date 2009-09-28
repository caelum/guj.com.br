package br.com.caelum.guj.logic;

import static br.com.caelum.vraptor.view.Results.logic;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.guj.model.Article;
import br.com.caelum.guj.model.ArticleLevel;
import br.com.caelum.guj.model.Category;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ApproveController {

	private final Result result;
	private final Session session;

	public ApproveController(Result result, Session session) {
		this.result = result;
		this.session = session;
	}

	public void list() {
		result.include("isModerator", true);
		result.include("articles", this.getAllArticles());
		result.include("categories", this.getAllCategories());
	}

	@SuppressWarnings("unchecked")
	protected List<Category> getAllCategories() {

		return session.createQuery(
				"from Category c ORDER BY c.name").setCacheable(false).list();

	}

	public void save(long articleId, long categoryId, String articleLevel) {

		Article article = this.getArticle(articleId);

		article.setApproved(true);
		article.setLevel(ArticleLevel.valueOf(articleLevel));

		Category category = new CategoryController(result, null, session).getCategory(categoryId);
		article.setCategory(category);

		result.include("isModerator", true);
		result.use(logic()).forwardTo(ApproveController.class).list();
	}

	public void delete(long id) {

		Article article = this.getArticle(id);

		session.delete(article);

		result.use(logic()).forwardTo(ApproveController.class).list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {

		return session.createQuery(
				"from Article a where a.approved = false ORDER BY a.date")
				.setCacheable(true).setCacheRegion("ApproveArticles").list();

	}

	private Article getArticle(long id) {

		return (Article) session.get(Article.class, id);
	}

}

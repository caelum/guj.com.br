package br.com.guj.logic;

import java.util.List;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.Parameter;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;

@Component("article")
public class ArticleLogic {
	private List<Article> articles;
	private Article article;

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
			.createQuery("from Article a ORDER BY a.id")
			.setCacheable(true)
			.setCacheRegion("Articles")
			.list();
	}

	private Article getArticleWithId(long id) {
		return (Article) HibernateUtil.getSessionFactory().getCurrentSession().get(Article.class, id);
	}

	public void list() {
		this.articles = getAllArticles();
	}

	public void show(@Parameter(key = "id") long id) {
		this.article = getArticleWithId(id);
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Article getArticle() {
		return article;
	}

}

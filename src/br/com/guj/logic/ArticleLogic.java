package br.com.guj.logic;

import java.util.List;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.Parameter;

import br.com.guj.Config;
import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.Category;
import br.com.guj.model.Post;

@Component("article")
public class ArticleLogic {
	private List<Post> postsBox;
	private List<Article> articlesBox;
	private List<Category> categories;
	private Article article;

	@SuppressWarnings("unchecked")
	private List<Category> getAllCategories() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
			.createQuery("from Category c ORDER BY c.name")
			.setCacheable(true)
			.setCacheRegion("Categories").list();
	}

	private Article getArticle(long id) {
		return (Article) HibernateUtil.getSessionFactory().getCurrentSession().get(Article.class, id);
	}

	public void list() {
		this.categories = this.getAllCategories();
//		this.articlesBox = getRandomArticles();
//		this.postsBox = getRandomPosts();
	}

	public void show(@Parameter(key = "id") long id) {
		this.article = this.getArticle(id);
//		this.articlesBox = getRandomArticles();
//		this.postsBox = getRandomPosts();
	}

	@SuppressWarnings("unchecked")
	private List<Post> getRandomPosts() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
			.createQuery("from Post a ORDER BY a.id")
			.setMaxResults(Config.getIntvalue("post.box.items"))
			.setCacheable(true)
			.setCacheRegion("BoxPosts").list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getRandomArticles() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
			.createQuery("from Article a ORDER BY a.id")
			.setMaxResults(Config.getIntvalue("article.box.items"))
			.setCacheable(true)
			.setCacheRegion("BoxArticles").list();
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

}

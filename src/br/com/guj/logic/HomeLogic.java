package br.com.guj.logic;

import java.util.List;

import org.vraptor.annotations.Component;

import br.com.guj.Config;
import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.Post;

/**
 * @author Rafael Steil
 */
@Component("home")
public class HomeLogic {
	private List<Post> posts;
	private List<Article> articles;

	@SuppressWarnings("unchecked")
	private List<Post> getAllPosts() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
			.createQuery("from Post p order by p.date desc").setMaxResults(
				Config.getIntvalue("posts.home.items")).setCacheable(
				true).setCacheRegion("homePosts").list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Article a order by a.id desc")
				.setMaxResults(Config.getIntvalue("article.home.items"))
				.setCacheable(true).setCacheRegion("homeArticles").list();
	}

	public void index() {
		this.posts = this.getAllPosts();
		this.articles = this.getAllArticles();
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public List<Post> getPosts() {
		return posts;
	}
}
